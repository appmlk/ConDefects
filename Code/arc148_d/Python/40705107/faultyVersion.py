def main():
    from sys import stdin, setrecursionlimit
    # setrecursionlimit(1000000)
    input = stdin.readline
    def iinput(): return int(input())
    def sinput(): return input().rstrip()
    def i0input(): return int(input()) - 1
    def linput(): return list(input().split())
    def liinput(): return list(map(int, input().split()))
    def miinput(): return map(int, input().split())
    def li0input(): return list(map(lambda x: int(x) - 1, input().split()))
    def mi0input(): return map(lambda x: int(x) - 1, input().split())
    INF = 1000000000000000000
    MOD = 1000000007

    N, M = miinput()
    A = liinput()

    B = set()
    for a in A:
        if a in B:
            B.discard(a)
        else:
            B.add(a)
    if not B:
        print('Bob')
        return
    
    if (len(B) // 2 & 1 == 0) and (M & 1 == 0):
        B = sorted(B)
        C, D = B[:N//2], B[N//2:]
        res = set()
        
        for c, d in zip(C, D):
            if c % (M // 2) != d % (M // 2):
                print('Alice')
                return
            print('Bob')
            return

    print('Alice')
        

main()