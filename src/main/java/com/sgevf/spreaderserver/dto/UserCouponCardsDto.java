package com.sgevf.spreaderserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCouponCardsDto {
    private int sellerId;
    private String sellerName;
    private String sellerLogo;
    private List<Coupon> cards;

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerLogo() {
        return sellerLogo;
    }

    public void setSellerLogo(String sellerLogo) {
        this.sellerLogo = sellerLogo;
    }

    public List<Coupon> getCards() {
        return cards;
    }

    public void setCards(List<Coupon> cards) {
        this.cards = cards;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public static class Coupon {
        private int id;
        private String getTime;
        private String endTime;
        private String status;
        private String discountRule;
        private String useRule;
        private int redPacketId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGetTime() {
            return getTime;
        }

        public void setGetTime(String getTime) {
            this.getTime = getTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDiscountRule() {
            return discountRule;
        }

        public void setDiscountRule(String discountRule) {
            this.discountRule = discountRule;
        }

        public String getUseRule() {
            return useRule;
        }

        public void setUseRule(String useRule) {
            this.useRule = useRule;
        }

        public int getRedPacketId() {
            return redPacketId;
        }

        public void setRedPacketId(int redPacketId) {
            this.redPacketId = redPacketId;
        }
    }

}
