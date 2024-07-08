#素因数分解を行う関数
#fc=[[prime,cnt], [prime, cnt]...]の二次元配列を返す
def factorization(n):
    now_num = n
    prime_cnt_list = []
    for i in range(2, n + 1):
        if i * i > now_num:
            break
        if now_num % i:
            continue
        cnt = 0
        while not now_num % i:
            now_num //= i
            cnt += 1
        prime_cnt_list.append((i, cnt))
    if now_num != 1:
        prime_cnt_list.append((now_num, 1))
    return prime_cnt_list

N = int(input())
ans = 0

for i in range(1, N+1):
    fc = factorization(i)
    odd = 1 #iに対してかけると平方数となる数を
    for p, cnt in fc:
        if cnt % 2 == 1:
            odd *= p
    
    for j in range(1, N+1):
        if odd*j*j > N:
            break
        ans += 1

print(ans)

