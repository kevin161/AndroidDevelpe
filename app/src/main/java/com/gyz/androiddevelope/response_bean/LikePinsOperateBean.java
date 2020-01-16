package com.gyz.androiddevelope.response_bean;

public class LikePinsOperateBean {

    /**
     * pin_id : 690448610
     * user_id : 15246080
     * seq : 52990507
     * liked_at : 1461060515
     */

    private LikeBean like;

    public LikeBean getLike() {
        return like;
    }

    public void setLike(LikeBean like) {
        this.like = like;
    }

    public static class LikeBean {
        private String pin_id;
        private String user_id;
        private int seq;
        private int liked_at;

        public String getPin_id() {
            return pin_id;
        }

        public void setPin_id(String pin_id) {
            this.pin_id = pin_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public int getLiked_at() {
            return liked_at;
        }

        public void setLiked_at(int liked_at) {
            this.liked_at = liked_at;
        }
    }
}
