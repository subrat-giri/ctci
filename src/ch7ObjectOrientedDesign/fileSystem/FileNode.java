package ch7ObjectOrientedDesign.fileSystem;

import java.util.HashSet;
import java.util.Set;

public class FileNode {
    private final boolean isDirectory;
    private String name;
    private FileNode parent;
    private Set<FileNode> files;
    private StringBuilder fileContent;

    public FileNode(String name, boolean isDirectory, FileNode parent) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.parent = parent;
        if (isDirectory) {
            files = new HashSet<>();
        } else {
            fileContent = new StringBuilder();
        }
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public Set<FileNode> getFiles() {
        return files;
    }

    public void setFiles(Set<FileNode> files) {
        this.files = files;
    }

    public void addFile(FileNode file) {
        this.files.add(file);
    }

    public String getFileContent() {
        if (isDirectory) {
            return null;
        }
        return fileContent.toString();
    }

    public String overWriteContent(String content) throws Exception {
        if (isDirectory) {
            return null;
        }
        fileContent.delete(0, fileContent.length());
        fileContent.append(content);
        return fileContent.toString();
    }

    public String appendContent(String content) {
        if (isDirectory) {
            return null;
        }
        fileContent.append(content);
        return fileContent.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileNode getParent() {
        return parent;
    }

    public void setParent(FileNode parent) {
        this.parent = parent;
    }
}
