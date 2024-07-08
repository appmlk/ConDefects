import sys
import heapq

def main():
    N = int(input())
    tl = []
    for _ in range(N):
        T, D = map(int, input().split())
        tl.append([T, T+D])

    tl.sort()

    q = []
    time = tl[0][0]
    ind = 0
    heapq.heapify(q)
    ans  = 0
    while ind < N or q:
        if ind < N and time < tl[ind][0]:       # 次の時間が先
            time = tl[ind][0]       # その時間にする
        while ind < N and time == tl[ind][0]:
            heapq.heappush(q, tl[ind][1])
            ind += 1
        while q:
            g = heapq.heappop(q)
            if g >= time:
                ans += 1
                break
        time += 1
    print(ans)

if __name__ == '__main__':
    main()

