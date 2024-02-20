package com.sai.server;

import org.springframework.stereotype.Service;
import com.sai.model.bankapplications;
import com.sai.repo.bankrepo;

@Service
public class Bankserviceimp implements Bankservice {

    private final bankrepo repo;

    public Bankserviceimp(bankrepo repo) {
        this.repo = repo;
    }
    @Override
    public bankapplications banksave(bankapplications Bankapplication) {
        return repo.save(Bankapplication);
    }
    @Override
    public bankapplications findById(int id) {
        return repo.findById(id).orElse(null);
    }
}
