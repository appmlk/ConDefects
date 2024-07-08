from collections import defaultdict
def main():
    N, M, L = mapint()
    A = lint()
    B = lint()
    
    unCombSet = defaultdict(set)
    is_reverse = False
    if N > M:
        N, M = M, N
        A, B = B, A
        is_reverse = True
    for i in range(L):
        x, y = mapint0()
        if is_reverse:
            x, y = y, x
        unCombSet[x].add(y)
    errprint(unCombSet)
    a_sort = sorted(range(N), key=lambda x: A[x], reverse=True)
    b_sort = sorted(range(M), key=lambda x: B[x], reverse=True)
    ans = 0
    for x, y in unCombSet.items():
        min_index = M - 1
        for i in range(M):
            if b_sort[i] in y:
                continue
            min_index = i
            break
        ans = max(ans, A[x] + B[b_sort[min_index]])
    for i in range(N):
        if a_sort[i] in unCombSet:
            continue
        ans = max(ans, A[a_sort[i]] + B[b_sort[0]])
        break
    print(ans)
    

def ini(): return int(input())
def mapint(): return map(int, input().split())
def mapint0(): return map(lambda x: int(x)-1, input().split())
def mapstr(): return input().split()
def lint(): return list(map(int, input().split()))
def lint0(): return list(map(lambda x: int(x)-1, input().split()))
def lstr(): return list(input().rstrip())
def errprint(*x): return None if atcenv else print(*x, file=sys.stderr) 

if __name__=="__main__":
    import sys, os
    input = sys.stdin.readline
    atcenv = os.environ.get("ATCODER", 0)

    main()

