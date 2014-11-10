package com.diosoft.training.project.security;

import com.khomenko.calendar.persistence.model.Agent;
import com.khomenko.calendar.persistence.repositories.AgentsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service("agentDetailsService")
public class AgentDetailsService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(AgentDetailsService.class);

    @Autowired
    AgentsRepository agentsRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        log.debug("Looking for user: " + username);
        Agent agent;
        agent = agentsRepository.findByUsername(username);
        log.debug("Agent found: " + agent);
        if (agent == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }
        return new AgentDetails(agent);
    }

}
