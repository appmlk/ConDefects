# B - Balls of Three Colors

MAX = 10 ** 8 + 1

def solve(r, g, b):
    def solve1(other1, other2):
        diff = other1 - other2
        if diff % 3 != 0:
            return MAX
        return other2 + diff
    
    ans = MAX
    ans = min(ans, solve1(max(g, b), min(g, b)))
    ans = min(ans, solve1(max(r, b), min(r, b)))
    ans = min(ans, solve1(max(g, r), min(g, r)))

    return -1 if ans == MAX else ans
    
t = int(input())
for _ in range(t):
    r, g, b = [int(e) for e in input().split()]
    print(solve(r, g, b))
