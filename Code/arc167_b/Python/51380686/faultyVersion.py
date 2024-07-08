def prime_factorize(N):
    # 答えを表す可変長配列
    res = {}

    # √N まで試し割っていく
    for p in range(2, N):
        # p * p <= N の範囲でよい
        if p * p > N:
            break

        # N が p で割り切れないならばスキップ
        if N % p != 0:
            continue

        # N の素因数 p に対する指数を求める
        e = 0
        while N % p == 0:
            # 指数を 1 増やす
            e += 1

            # N を p で割る
            N //= p

        # 答えに追加
        res[p]=e

    # 素数が最後に残ることがありうる
    if N != 1:
        res[N]=1

    return res

A,B=map(int,input().split())
res=prime_factorize(A)


pro=1
for key in res.keys():
    pro*=B*res[key]+1

ans=10**30
for key in res.keys():
    ans=min(ans,(B*res[key]*pro//2)//res[key])
print(ans%998244353)
    
    