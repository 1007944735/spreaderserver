package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.entity.Card;

import java.util.List;

public interface CardService {
    List<Card> queryCardByBusinessId(int businessId);

    int deleteCard(int id);

    int insertCard(Card card);
}
