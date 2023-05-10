package org.carbonit.box;

public enum EMovement {

    D("Right"), G("Left"), A("Walks");

    private String movement;

    EMovement(String movement) {
        this.movement = movement;
    }
}
