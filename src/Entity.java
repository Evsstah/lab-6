import java.lang.reflect.Field;

abstract class Entity {
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Field[] fields = this.getClass().getDeclaredFields();

        result.append(this.getClass().getSimpleName()).append("{"); // Добавляем название класса

        for (Field field : fields) {
            field.setAccessible(true); // Позволяем доступ к приватным полям
            try {
                result.append(field.getName()).append("=").append(field.get(this)).append(", ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // Удаляем последнюю запятую и пробел
        if (result.length() > 1) {
            result.setLength(result.length() - 2);
        }
        result.append("}"); // Закрываем фигурную скобку
        return result.toString();
    }
}