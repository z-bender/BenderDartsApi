package ru.bender.darts.api.interfaces;

/**
 * Интерфейс для UI-элемента, в котором будет запрашиваться у пользователя количество бросков игрока
 */
public interface LastShotsCounter {
    /**
     * @return Возвращает количество бросков игрока
     */
    short getLastShotsCount();
}
