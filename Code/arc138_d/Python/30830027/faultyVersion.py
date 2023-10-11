import math

def map_int(s):
    try:
        return list(map(int, s.split()))
    except ValueError:
        return s.strip().split()

def main():
    (n,k), = [map_int(s) for s in open(0)]
    s = set()
    for i in range(2 ** n):
        j = i
        t = 0
        while i:
            t += i % 2
            i >>= 1
        if t == k:
            s.add(j)
    ans = [0]
    for i in range(n):
        if len(s) == 0:
            print(-1)
            return
        for ss in s:
            break
        t = ans[-1] ^ ss
        for a in ans[:1 << i]:
            at = a ^ t
            if at in s:
                s.remove(at)
            ans.append(at)
    print('Yes')
    print(*ans)
    

if __name__ == '__main__':
    main()