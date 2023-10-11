# エラトステネスの篩
def Eratosthenes(N):
    # 素数であるかの判定リスト
    IsPrime=[True]*(N+1)

    # i=2,3,4,...
    i=2
    # i≤√Nまで⇔i^2≤Nまで
    while i**2<=N:
        # iが素数でなければ
        if IsPrime[i]==False:
            # 次のiへ
            i+=1
            continue
       
        # k=2,3,4,...
        k=2
        while i*k<=N:
            # iの倍数は素数でない
            IsPrime[i*k]=False
            # 次のkへ
            k+=1

        # 次のkへ
        i+=1

    # 素数リスト
    PList=[]

    # i=2~N
    for i in range(2,N+1):
        # iが素数ならば
        if IsPrime[i]==True:
            # リストへ入れる
            PList.append(i)

    # リストを返す
    return PList
    
N = int(input())

Alist = Eratosthenes(10**3)
lenAlist = len(Alist)
k=lenAlist-1
ans = 0
for i in range(lenAlist):
    while i<k and N<Alist[i]*Alist[k]**3:
        k-=1
    if k<=i:
        print(ans)
        exit()
    ans+=k-i