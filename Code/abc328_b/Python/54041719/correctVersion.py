INT =lambda: int(input())
MI = lambda: map(int,input().split())
MI_DEC = lambda: map(lambda x: int(x) -1, input().split())
LI = lambda: list(map(int, input().split()))
def main() -> None:
    n = INT()
    s = LI()
    count = 0
    for m, di in enumerate(s, start=1):
        for d in range(1, di+1):
            ss = set(str(m)).union(set(str(d)))
            if  len(ss) == 1:
                count += 1
    print(count)
if __name__ == '__main__':
    main()