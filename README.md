# Simulation2D
Проект “симуляция”
Пошаговая симуляция 2D мира, населённого травоядными и хищниками. Кроме существ, мир содержит ресурсы (траву), которыми питаются травоядные, и статичные объекты, с которыми нельзя взаимодействовать. 2D мир представляет из себя матрицу NxM, каждое существо или объект занимают клетку целиком, нахождение в клетке нескольких объектов/существ - недопустимо.

Травоядное - стремится найти ресурс (траву), может потратить свой ход на движение в сторону травы, либо на её поглощение.

Хищник - стремится найти травоядное, может потратить свой ход на движение в сторону травоядного, либо на его поглощение.

Поиск кратчайшего пути у животных осуществляется с применением алгоритма «поиск в ширину»

![image](https://github.com/GenesizAnt/Simulation2D/assets/32221627/9014c0be-53a2-44da-9b6b-6e57d7b3d645)

