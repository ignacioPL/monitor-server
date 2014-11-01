package edu.soa.pdroid.server.model;

/**
 * Created by ignacio on 18/08/14.
 */
public class OsProcess {

    private String pid;
    private String name;
    private String pcpu;
    private String state;
    private String pmem;

    public OsProcess() {
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPcpu() {
        return pcpu;
    }

    public void setPcpu(String pcpu) {
        this.pcpu = pcpu;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPmem() {
        return pmem;
    }

    public void setPmem(String pmem) {
        this.pmem = pmem;
    }

}
