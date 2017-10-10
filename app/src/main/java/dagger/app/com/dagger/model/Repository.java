package dagger.app.com.dagger.model;

/**
 * Created by 611399999 on 05/10/2017.
 */

public class Repository {
    private String repositoryName = null;
    private String repositoryDescription = null;

    public Repository(String repositoryName, String repositoryDescription) {
        this.repositoryName = repositoryName;
        this.repositoryDescription = repositoryDescription;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public String getRepositoryDescription() {
        return repositoryDescription;
    }
}
