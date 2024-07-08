from collections import defaultdict

T = int(input())

def solve2(x: str, y:str) -> bool:
    cnt_x: defaultdict[str, int] = defaultdict(int)
    cnt_y: defaultdict[str, int] = defaultdict(int)
    for c in x:
        cnt_x[c] += 1
    for c in y:
        cnt_y[c] += 1
    if (cnt_x["A"] > cnt_y["A"] or cnt_x["B"] > cnt_y["B"]):
        return False
    n = cnt_y["A"] - cnt_x["A"]
    l = list(x)
    for i in range(len(l)):
        if l[i] == "C":
            if n > 0:
                l[i] = "A"
                n -= 1
            else:
                l[i] = "B"
    x = "".join(l)
    print(x, y)
    cy, cx = 0, 0
    for xx, yy in zip(x, y):
        cx += 1 if xx == "B" else 0
        cy += 1 if yy == "B" else 0
        if cx > cy:
            return False
    return True

def solve(x: str, y: str) -> bool:
    y_s = y.split("C")
    i = 0
    x_s: list[str] = []
    for j, s in enumerate(y_s):
        idx = i + len(s)
        if (j+1 != len(y_s) and x[idx] != "C"):
            return False
        else:
            x_s.append(x[i:idx])
        i = idx + 1
    return all([solve2(x, y) for x, y in zip(x_s, y_s)])

for _ in range(T):
    n, x, y = input().split()
    if solve(x, y):
        print("Yes")
    else:
        print("No")
