package com.sai.server;

import com.sai.model.bankapplications;

public interface Bankservice {
   public bankapplications banksave(bankapplications Bankapplication);

   public bankapplications findById(int id);
}
