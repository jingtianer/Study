package com.nequer.android.business.account.model.response;

public class LoginRspModel {

    /**
     * code : 0
     * data : {"token":"20ae03cbc6d349cb902f93cf60d3ba89","user":{"name":"han1254","signature":"","avatar":"http://cdn.marklux.cn/4f65ff34-462d-401c-87b7-e350ecc9fe53","sex":-1,"createdAt":1556170713803,"updatedAt":1556170713803}}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : 20ae03cbc6d349cb902f93cf60d3ba89
         * user : {"name":"han1254","signature":"","avatar":"http://cdn.marklux.cn/4f65ff34-462d-401c-87b7-e350ecc9fe53","sex":-1,"createdAt":1556170713803,"updatedAt":1556170713803}
         */

        private String token;
        private UserBean user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * name : han1254
             * signature :
             * avatar : http://cdn.marklux.cn/4f65ff34-462d-401c-87b7-e350ecc9fe53
             * sex : -1
             * createdAt : 1556170713803
             * updatedAt : 1556170713803
             */

            private String name;
            private String signature;
            private String avatar;
            private int sex;
            private long createdAt;
            private long updatedAt;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public long getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(long createdAt) {
                this.createdAt = createdAt;
            }

            public long getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(long updatedAt) {
                this.updatedAt = updatedAt;
            }
        }
    }
}
