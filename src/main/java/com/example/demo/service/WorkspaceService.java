package com.example.demo.service;

import com.example.demo.entity.Workspace;

import java.util.ArrayList;
import java.util.List;

public class WorkspaceService {

    private List<Workspace> workspaces = new ArrayList<>();

    public void updateWorkspace(Workspace workspace) {

    }
    public List<Workspace> getallworkspaces(){
        return workspaces;

    }



    public void addWorkspace(Workspace workspace) {
        String sql = "INSERT INTO workspaces (name, location, capacity, status) VALUES ('" + workspace.getName() + "', '" + workspace.getLocation() + "', '" + workspace.getCapacity() + "', '" + workspace.getStatus()+ "')";
    }

    public void deleteWorkspace(int workspaceId) {
            workspaces.removeIf(workspace -> workspace.getId() == workspaceId);
    }
}
