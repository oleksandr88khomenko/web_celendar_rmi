package com.diosoft.training.project.persistence.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by oleksandr_khomenko on 07.11.2014.
 */
@Repository
public class SequenceDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    public SequenceDAO() {

    }
    public SequenceDAO(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Long getNextSequenceId(String key) {
        Query query = new Query(Criteria.where("_id").is(key));
        Update update = new Update();
        update.inc("sequence", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        Sequence sequence = mongoTemplate.findAndModify(query, update, options, Sequence.class);

        if(sequence == null) throw new SequenceException("Unable to get sequence for key: " + key);

        return sequence.getSequence();
    }
}