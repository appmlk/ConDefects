N, M = map(int, input().split())
S = [list(input()) for _ in range(N)]

ans = M + 10

for bit in range(1 << N): #訪れるポップコーン売り場の選び方＝bit

    store = []
    for i in range(N): #N個の売り場について
        if bit >> i & 1: #bitのi番目のビットが1かどうか pythonで1はTrue, 0はFalse
            store.append(i) #bitで1だった売り場のindexをstoreに入れる
    
    data = [0] * M
    for s_i in store: #storeに格納された売り場のindex=s_iについて
        for j, s_j in enumerate(S[s_i]):
            if s_j == 'o': #s_i番目の売り場のj番目の要素s_jがoだったら
                data[j] == 1
    
    if data == [1] * M:
        ans = min(ans, len(store))

print(ans)