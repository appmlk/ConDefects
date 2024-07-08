A, B, C, D = map(int, input().split())

p = [1, 2, 1, 0, 1, 2, 1, 0]

P = p[A%4+(B+1)%2:A%4+(B+1)%2+4]
S = ((C - A) // 4) * 4 + sum(P[:(C-A)%4])

Q = p[A%4+B%2:A%4+B%2+4]
T = ((C - A) // 4) * 4 + sum(Q[:(C-A)%4])

ans = (S+T) * ((D-B) // 2) + ((D - B) % 2) * ((D%2)*S + ((D+1)%2)*T)
print(ans)
