package com.diosoft.training.project.security;


import com.khomenko.calendar.persistence.model.Agent;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;

public class AgentDetails extends User {

    private Agent agent;

    public AgentDetails(Agent agent) {
        super(agent.getUsername(), agent.getPassword(), Arrays.asList(new SimpleGrantedAuthority("user")));
        this.agent = agent;
    }

    public Agent getAgent() {
        return agent;
    }

    public static AgentDetails u() {
        return (AgentDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
