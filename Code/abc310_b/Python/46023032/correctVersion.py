N, M = map(int, input().split())
product = [list(map(int, input().split())) for _ in range(N)]
product.sort(reverse=True)
answer = False

for i in range(N):
    for j in range(i+1, N):
        Pi = product[i][0]
        Pj = product[j][0]
        Fi = set(product[i][2:])
        Fj = set(product[j][2:])
        if Fi <= Fj and (Pi > Pj or Fi < Fj):
            answer = True
        if Pi == Pj and (Fi < Fj or Fi > Fj):
            answer = True
            
print("Yes" if answer else "No")                