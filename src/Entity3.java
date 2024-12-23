import java.lang.reflect.Field;

// Абстрактный класс Entity3, который переопределяет метод toString()
abstract class Entity3 {
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(); // Создаем объект StringBuilder для построения строки
        Field[] fields = this.getClass().getDeclaredFields(); // Получаем все поля текущего класса

        // Проверяем, помечен ли класс аннотацией @ToString
        ToString classAnnotation = this.getClass().getAnnotation(ToString.class);
        boolean classAnnotatedWithNo = classAnnotation != null && classAnnotation.value() == ToString.Value.NO;

        result.append(this.getClass().getSimpleName()).append("{"); // Добавляем название класса и открывающую фигурную скобку

        for (Field field : fields) {
            field.setAccessible(true); // Позволяем доступ к приватным полям
            ToString fieldAnnotation = field.getAnnotation(ToString.class); // Получаем аннотацию @ToString для текущего поля
            boolean fieldAnnotatedWithYes = fieldAnnotation != null && fieldAnnotation.value() == ToString.Value.YES;
            boolean fieldAnnotatedWithNo = fieldAnnotation != null && fieldAnnotation.value() == ToString.Value.NO;

            // Проверяем условия для включения поля в строковое представление
            if ((!classAnnotatedWithNo && !fieldAnnotatedWithNo) || fieldAnnotatedWithYes) {
                try {
                    result.append(field.getName()).append("=").append(field.get(this)).append(", ");
                    // Добавляем имя поля и его значение в строку
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); // Обрабатываем исключение, если нет доступа к полю
                }
            }
        }
        // Удаляем последнюю запятую и пробел, если они есть
        if (result.length() > 1 && result.charAt(result.length() - 1) == ',') {
            result.setLength(result.length() - 2);
        }
        result.append("}"); // Закрываем фигурную скобку
        return result.toString(); // Возвращаем построенную строку
    }
}
