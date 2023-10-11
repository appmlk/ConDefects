class Motion:
    def __init__(self) -> None:
        self.dx = 1
        self.dy = 0
        self.x = 0
        self.y = 0

    def rotate(self) -> None:
        self.dx, self.dy = self.y, -self.dx

    def move(self) -> None:
        self.x += self.dx
        self.y += self.dy

    @property
    def get_x(self) -> int:
        return self.x

    @property
    def get_y(self) -> int:
        return self.y


n = int(input())
t = input()

motion = Motion()
for c in t:
    if c == "S":
        motion.move()
    else:
        motion.rotate()

print(f"{motion.get_x} {motion.get_y}")
