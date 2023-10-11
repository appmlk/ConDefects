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

    N, K = miinput()
    A = liinput()
    intervals = [[0, 1]]
    A.sort()
    for a in A:
        tmp = []
        for l, r in intervals:
            tmp.append([l+a, r+a])
        intervals = merger(intervals + tmp)
        if len(intervals) > 10 * K:
            break
    
    ans = []
    for (_, r), (l, _) in zip(intervals, intervals[1:]):
        for x in range(r, l):
            ans.append(x)
            if len(ans) == K:
                print(*ans)
                return
    tmp = intervals[-1][1]
    while len(ans) < K:
        ans.append(tmp)
        tmp += 1
    print(*ans)
    
def merger(intervals):
    intervals.sort(key=lambda interval: interval[0])
    merged = [intervals[0]]
    for w in intervals:
        if w[0] > merged[-1][1]:
            merged.append(w)
        elif w[1] > merged[-1][1]:
            merged[-1][1] = w[1]
    return merged

main()