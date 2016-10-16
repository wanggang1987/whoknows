/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whoknows.domain;

import java.sql.Timestamp;

public class Picture {

    private Long id;
    private String name;
    private String width;
    private String height;
    private String stream_b;
    private String string_mb;
    private Timestamp create_time;
    private Timestamp view_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getStream_b() {
        return stream_b;
    }

    public void setStream_b(String stream_b) {
        this.stream_b = stream_b;
    }

    public String getString_mb() {
        return string_mb;
    }

    public void setString_mb(String string_mb) {
        this.string_mb = string_mb;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getView_time() {
        return view_time;
    }

    public void setView_time(Timestamp view_time) {
        this.view_time = view_time;
    }
}
