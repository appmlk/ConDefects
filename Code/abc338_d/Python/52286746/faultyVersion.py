
def main():
    N, M = map(int, input().split())
    A = list(map(int, input().split()))

    cost = [0]*(2*N)
    ans = 0
    for i in range(M-1):
        s, t = A[i], A[i+1]
        s, t = min(s, t), max(s, t)
        if 2*(t-s) < N:
            ans += t-s
            d = N + 2*s - 2*t
            cost[s] += d
            cost[t+1] -= d
        else:
            ans += N+s-t
            d = - N - 2*s + 2*t
            cost[t] += d
            cost[N+s] -= d

    for i in range(2*N-1):
        cost[i+1] += cost[i]

    _cost = []
    for i in range(N):
        _cost.append(cost[i] + cost[N+i])
    print(ans+min(_cost))

if __name__ == '__main__':
    main()
