package edu.soa.monitorserver.server.model;

/**
 * Created by ignacio on 19/10/14.
 */
public class ServerMemoryUsage {

    private String totalMemory;
    private String usedMemory;
    private String freeMemory;

    public ServerMemoryUsage() {
    }

    public String getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(String totalMemory) {
        this.totalMemory = totalMemory;
    }

    public String getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(String usedMemory) {
        this.usedMemory = usedMemory;
    }

    public String getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(String freeMemory) {
        this.freeMemory = freeMemory;
    }

}
