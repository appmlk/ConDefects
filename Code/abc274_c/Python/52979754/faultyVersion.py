def main() -> None:
    n: int = int(input())
    d: dict[int, int] = dict.fromkeys(range(2 * n + 1), 0)
    a: list[int] = list(map(int, input().split()))
    for i, ai in enumerate(a):
        d[(i + 1) * 2] = d[ai] + 1
        d[(i + 1) * 2 + 1] = d[ai] + 1
    for i in d.values():
        print(i)


main()
