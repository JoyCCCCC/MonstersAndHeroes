import java.util.List;

public interface RoleFactory<T extends Role> {
    void generateRoles();
    T create(List<Hero> heroes);
}
