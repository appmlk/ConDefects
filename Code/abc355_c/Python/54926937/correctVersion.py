N,T = map(int, input().split())
A = list(map(int, input().split()))

horizontal = [[] for _ in range(N)]
vertical = [[] for _ in range(N)]
diagonal = [[],[]]

ans = -1
for i,a in enumerate(A):
    row = (a-1) // N
    col = (a-1) % N
    horizontal[row].append(a)
    vertical[col].append(a)
    if row == col: diagonal[0].append(a)
    if row+col == N-1: diagonal[1].append(a)
    if (len(horizontal[row]) == N) or (len(vertical[col]) == N) or (len(diagonal[0])) == N or (len(diagonal[1])) == N:
        ans = i+1
        break
print(ans)