package ch7ObjectOrientedDesign.fileSystem;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class FileSystem {
    private FileNode root;

    public FileSystem() {
        root = new FileNode("/", true, null);
    }

    public List<String> ls(String path) {
        if ("/".equals(path)) {
            return root.getFiles().stream().map(FileNode::getName).toList();
        }
        String[] directories = path.substring(1).split("/");
        FileNode n = root;
        for (String str : directories) {
            Set<FileNode> files = n.getFiles();
            FileNode node = files.stream().filter(file -> str.equals(file.getName())).findAny().orElse(null);
            if (node == null) {
                return Collections.emptyList();
            } else {
                if (node.isDirectory()) {
                    n = node;
                } else {
                    return List.of(node.getName());
                }
            }
        }
        return n.getFiles().stream().map(FileNode::getName).sorted(String::compareTo).toList();
    }

    public void mkdir(String path) {
        String[] directories = path.substring(1).split("/");
        FileNode n = root;
        for (String str : directories) {
            Set<FileNode> files = n.getFiles();
            FileNode node = files.stream().filter(file -> str.equals(file.getName())).findAny().orElse(null);
            if (node == null) {
                node = new FileNode(str, true, n);
                n.addFile(node);
            }
            n = node;
        }
    }

    public void addContentToFile(String filepath, String content) {
        String[] cFiles = filepath.substring(1).split("/");
        FileNode n = root;
        for (String str : cFiles) {
            Set<FileNode> files = n.getFiles();
            FileNode node = files.stream().filter(file -> str.equals(file.getName())).findAny().orElse(null);
            if (node == null) {
                node = new FileNode(str, false, n);
                n.addFile(node);
                node.appendContent(content);
            } else {
                if (node.isDirectory()) {
                    n = node;
                } else {
                    node.appendContent(content);
                }
            }
        }
    }

    public String readContentFromFile(String filePath) {
        String[] cFiles = filePath.substring(1).split("/");
        FileNode n = root;
        for (String str : cFiles) {
            Set<FileNode> files = n.getFiles();
            FileNode node = files.stream().filter(file -> str.equals(file.getName())).findAny().orElse(null);
            if (node == null) {
                return "";
            } else {
                if (node.isDirectory()) {
                    n = node;
                } else {
                    return node.getFileContent();
                }
            }
        }
        return "";
    }

}
