def create_end_wall(hall):
    return "+" * (2 * M - 1) + hall + "+"


def create_road(walls):
    if isinstance(walls, int):
        walls = [walls]
    walls = set(map(lambda x: M - x - 2, walls))
    ret = ["+o"]

    for x in range(M - 1):
        if x in walls:
            ret.append("|")
        else:
            ret.append(".")
        ret.append("o")

    ret.append("+")
    return "".join(ret)


def create_wall(roads):
    if isinstance(roads, int):
        roads = [roads]
    roads = set(map(lambda x: M - x - 1, roads))
    ret = ["+"]

    for x in range(M):
        if x in roads:
            ret.append(".")
        else:
            ret.append("-")
        ret.append("+")

    return "".join(ret)


N, M, K = map(int, input().split())
if N % 2 != K % 2 or N * M < K or K < N:
    print("No")
    exit()

if N * M > K + 2:
    K += 2

print("Yes")
remain = K - N

answer = [create_end_wall("S")]

if N % 2 == 0 or (N * M - M + 1) >= K:
    remain //= 2
    div, mod = 0, 0
    if M - 1 > 0:
        div, mod = remain // (M - 1), remain % (M - 1)

    for _ in range(div):
        answer.append(create_road(-1))
        answer.append(create_wall(M - 1))
        answer.append(create_road(-1))
        answer.append(create_wall(0))

    if mod:
        answer.append(create_road(mod))
        answer.append(create_wall(mod))
        answer.append(create_road(mod))
        answer.append(create_wall(0))

    while len(answer) < N * 2 + 1:
        answer.append(create_road(0))
        answer.append(create_wall(0))

    answer[-1] = create_end_wall("G")

else:
    for i in range(N // 2 - 1):
        answer.append(create_road(-1))
        answer.append(create_wall(M - 1))
        answer.append(create_road(-1))
        answer.append(create_wall(0))
    answer.append(create_road(-1))
    answer.append(create_wall(M - 1))

    remain = K - (N - 2) * M - M
    top = True

    top_walls = []
    halls = []
    bottom_walls = []

    idx = M - 2
    for _ in range(remain):
        if top:
            top_walls.append(idx)
        else:
            bottom_walls.append(idx)
        halls.append(idx + 1)
        idx -= 1
        top = not top

    answer.append(create_road(top_walls))
    answer.append(create_wall(halls))
    answer.append(create_road(bottom_walls))
    answer.append(create_end_wall("G"))

for ans in answer:
    print(ans)