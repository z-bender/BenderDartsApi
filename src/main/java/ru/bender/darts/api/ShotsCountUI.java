package ru.bender.darts.api;

/**
 * Интерфейс для UI-элемента, в котором будет запрашиваться у пользователя количество бросков игрока
 */
public interface ShotsCountUI {
    /**
     * @return Возвращает количество бросков игрока
     */
    short getShotsCount();
}
