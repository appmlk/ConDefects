def solve(N):
    if N == 1:
        return [1]
    if N == 2:
        return None

    ans = [2]
    used = set([3, 6])
    for _ in range(N - 3):
        for v in list(used):
            if v + 1 not in used and v * (v + 1) not in used:
                used.discard(v)
                used.add(v + 1)
                used.add(v * v + v)
                break

    return [2] + list(used)


def main():
    T = int(input())
    for t in range(T):
        N = int(input())
        ans = solve(N)
        if ans is None:
            print("No")
        else:
            print("Yes")
            print(*ans)


main()
