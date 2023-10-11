N,a,b=map(int,input().split())
A=list(map(int,input().split()))
def is_ok(mid):
    lcnt=0
    hcnt=0
    for i in A:
        if mid>i:
            if (mid-i)%a==0:
                lcnt+=(mid-i)//a
            else:
                lcnt+=(mid-i)//a+1
        else:
            hcnt+=(i-mid)//b
    if lcnt<=hcnt:
        return True
    else:
        return False
                
def binary_search(ok, ng):
    """二分探索

    Parameters
    ----------
    ok : int
    ng : int

    Returns
    -------
    ok : int
    """
    while abs(ng - ok) > 1:
        mid = (ok + ng) // 2
        if is_ok(mid):
            ok = mid
        else:
            ng = mid
    return (ok+ng)//2
print(binary_search(1,10**9))