K, G, M = map(int, input().split())
Gi, Mi = 0, 0
for _ in range(K):
    if Gi == G:
        Gi = 0
    elif Mi == 0:
        Mi = M
    else:
        if Gi + Mi > G:
            Gi = G
            Mi = Mi - (G - Gi)
        else:
            Gi = Gi + Mi
            Mi = 0
print(Gi, Mi)