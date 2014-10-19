package edu.soa.monitorserver.server.model;

/**
 * Created by ignacio on 19/10/14.
 */
public class FileSystemUsage {

    private String filesystem;
    private String size;
    private String used;
    private String available;
    private String usePercen;
    private String mountedOn;

    public FileSystemUsage() {
    }

    public String getFilesystem() {
        return filesystem;
    }

    public void setFilesystem(String filesystem) {
        this.filesystem = filesystem;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getUsePercen() {
        return usePercen;
    }

    public void setUsePercen(String usePercen) {
        this.usePercen = usePercen;
    }

    public String getMountedOn() {
        return mountedOn;
    }

    public void setMountedOn(String mountedOn) {
        this.mountedOn = mountedOn;
    }
}
