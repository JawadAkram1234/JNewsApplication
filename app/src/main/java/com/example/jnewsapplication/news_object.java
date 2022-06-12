package com.example.jnewsapplication;

public class news_object {

    private Integer n_id;
    private String image_url;
    private String n_link;
    private String n_heading;
    private String d_news;
    private String n_time;

    // ACCESS MODIFIERS: SETTERS AND GETTERS
    public void set_news_id(Integer id) {
        this.n_id = id;
    }

    public void set_news_h(String hd) {
        this.n_heading = hd;
    }

    public void set_news_l(String l) {
        this.n_link = l;
    }

    public void set_news_t(String t) {
        this.n_time = t;
    }

    public void set_news_d(String d) {
        this.d_news = d;
    }

    public void set_image_url(String u) {
        this.image_url = u;
    }

    public String get_news_h() {
        return n_heading;
    }

    public String get_news_d() {
        return d_news;
    }

    public String get_image_url() {
        return image_url;
    }
    public String get_news_t() {
        return n_time;
    }

    public String get_news_l() {
        return n_link;
    }

    public Integer get_news_id() {
        return n_id;
    }


}