package com.Common.Util;

public enum Status{
    PUBLISHED (0),
    UNPUBLISHED (0);

    private final int status;

    Status(int status) {
        this.status = status;
    }
}