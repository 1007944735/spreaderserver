package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.dao.CardMapper;
import com.sgevf.spreaderserver.entity.Card;
import com.sgevf.spreaderserver.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardMapper cardMapper;

    @Override
    public List<Card> queryCardByBusinessId(int businessId) {
        return cardMapper.queryBusinessCard(businessId);
    }

    @Override
    public int deleteCard(int id) {
        return cardMapper.deleteCard(id);
    }

    @Override
    public int insertCard(Card card) {
        return cardMapper.insertCard(card);
    }
}
