package com.example.digitalcampuscanteen;

public class UserModel {
        String uid;
        String imageUrl;
        String name;
        String email;
        String mobile;
        String userStatus;

        public UserModel(String uid, String imageUrl, String name, String email, String mobile, String userStatus) {
            this.uid = uid;
            this.imageUrl = imageUrl;
            this.name = name;
            this.email = email;
            this.mobile = mobile;
            this.userStatus = userStatus;

        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(String userStatus) {
            this.userStatus = userStatus;
        }

    }

