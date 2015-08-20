package com.Repository;

import org.json.simple.JSONObject;

public class User implements Builder {
    private String login;
    private String password;
    private String email;
    private String name;
    private int id;
    private User user;
    private JSONObject jsonObject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    private User(UserBuilder userBuilder) {
        this.login = userBuilder.login;
        this.password = userBuilder.password;
        this.email = userBuilder.email;
        this.name = userBuilder.name;
        this.id = userBuilder.id;
    }

    @Override
    public User buildUser() {
        return new UserBuilder(login, password).name(name).email(email).build();
    }

    @Override
    public String toString() {
        return user.getLogin();
    }

    public static class UserBuilder {
        protected User user;
        private String login;
        private String password;
        private String name;
        private String email;
        private int id;

        public UserBuilder(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public UserBuilder id(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            user = new User(this);
            if (user.getPassword().length() < 4) {
                throw new IllegalStateException("Password must contain at least 4 characters!"); // thread-safe
            }
            return user;
        }
    }
}
