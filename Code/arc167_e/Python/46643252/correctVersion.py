from sys import stdin
def input():
    return stdin.readline().rstrip("\n")

def solve():
    s = int(input())
    if s in [1, 2, 3, 5, 7]:
        print("No")
        return
    print("Yes")
    a = s // 2
    if s % 2 == 0:
        print(0, 0, a, a, a - 2, a)
    else:
        print(0, 0, a, a - 1, a - 1, a - 4)
case_t = 1
case_t = int(input())
for _ in [None] * case_t:
    solve()


