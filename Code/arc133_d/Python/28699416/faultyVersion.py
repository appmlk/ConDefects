"""
a = 4 * x
a ^ (a + 1) ^ (a + 2) ^ (a + 3) = 0

l_0 := (l - 1) // 4 * 4
r_0 := r // 4 * 4

(l_0 ^ ... ^ l - 1) ^ (r_0 ^ ... ^ r) == V

下2桁とそれ以外に分ける
a = 4 * x
a = a
a ^ (a + 1) = 1
a ^ (a + 1) ^ (a + 2) = a + 3
a ^ (a + 1) ^ (a + 2) ^ (a + 3) = 0
"""
MOD = 998244353

l, r, v = map(int, input().split())
l -= 1
X = [0, 1, 3, 0]
v_0 = v % 4
v_1 = v // 4
bl = v_1.bit_length() - 1


def solve(l, r, a, b):
    if a % 2 == 1 and b % 2 == 1:
        if v_1 != 0:
            return 0
        else:
            d = r - l + 1
            if a < b:
                return (d + 1) * d // 2
            else:
                return d * (d - 1) // 2
    
    elif a % 2 == 1:
        if l <= v_1 <= r:
            if a < b:
                return v_1 - l + 1
            else:
                return v_1 - l
        else:
            return 0
            
    elif b % 2 == 1:
        if l <= v_1 <= r:
            if a < b:
                return r - v_1 + 1
            else:
                return r - v_1
        else:
            return 0
    elif v_1 == 0:
        if a < b:
            return r - l + 1
        else:
            return 0
    else:
        """
        l <= x < y <= r
        x ^ y == v_0
        の数え上げ
        a < b
        であれば x <= y になる
        x = y は v_1 = 0 の時だけだから無視して良い
        """
        
        memo = {}
        """
        x := x がl以上をすでに満たしてる
        y := y がr以下をすでに満たしてる
        ll := 下から何桁目か
        eq := x == y
        """
        def dfs(x, y, ll, eq):
            if ll == -1:
                return x == -1 and y == -1
            if (x, y, ll, eq) in memo:
                return memo[(x, y, ll, eq)]
            
            def plus(nx, ny, eq):
                if ny == -1:
                    pass
                elif ny > r:
                    return 0
                elif ny + (1 << ll) - 1 <= r:
                    ny = -1
                
                if nx == -1:
                    pass
                elif nx >= l:
                    nx = -1
                elif nx + (1 << ll) - 1 < l:
                    return 0
                
                return dfs(nx, ny, ll - 1, eq)
            
            ret = 0
            if v_1 >> ll & 1:
                # x に渡す
                if not eq:
                    if x == -1:
                        nx = -1
                    else:
                        nx = x + (1 << ll)
                    
                    ret += plus(nx, y, False)
                
                # y に渡す
                if y == -1:
                    ny = y
                else:
                    ny = y + (1 << ll)
                ret += plus(x, ny, False)
            
            else:
                # 両方に渡さない
                ret += plus(x, y, eq)
                # 両方に渡す
                if x == -1:
                    nx = -1
                else:
                    nx = x + (1 << ll)
                if y == -1:
                    ny = y
                else:
                    ny = y + (1 << ll)
                ret += plus(nx, ny, eq)
            ret %= MOD
            memo[(x, y, ll, eq)] = ret
            return ret
                    
        
        if (1 << (bl + 1)) - 1 < l:
            return 0
        if l == 0:
            ll = -1
        else:
            ll = 0
            
        if r >= ((1 << (bl + 1)) - 1):
            rr = -1
        else:
            rr = 0
        
        return dfs(ll, rr, bl, True)
        
        ret = 0
        for x in range(l, r + 1):
            y = x ^ v_1
            if x ^ y == v_1 and x <= y <= r:
                
                ret += 1
        return ret
        
ans = 0
for a in range(4):
    for b in range(4):
        if X[a] ^ X[b] != v_0:
            continue
        ll = (l - a + 3) // 4
        assert l <= ll * 4 + a < l + 4
        rr = (r - b) // 4
        assert r - 4 < rr * 4 + b <= r
        if ll > rr:
            continue
        ans += solve(ll, rr, a, b)
        ans %= MOD

print(ans)
        
        
        

