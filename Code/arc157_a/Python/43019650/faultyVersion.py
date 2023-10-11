from collections import defaultdict
 
class UnionFind():
    #「uf = UnionFind(頂点の数)」で初期化
    def __init__(self, n):
        self.n = n
        self.parents = [-1] * n
 
    def find(self, x): #uf.find(x)
        #要素xが属するグループの根を返す
        if self.parents[x] < 0:
            return x
        else:
            self.parents[x] = self.find(self.parents[x])
            return self.parents[x]
 
    def union(self, x, y): #uf.union(x, y)
        #要素xが属するグループと要素yが属するグループを併合
        x = self.find(x)
        y = self.find(y)
 
        if x == y:
            return
 
        if self.parents[x] > self.parents[y]:
            x, y = y, x
 
        self.parents[x] += self.parents[y]
        self.parents[y] = x
 
    def size(self, x): #uf.size(x)
        #要素xが属するグループのサイズ(要素数)を返す
        return -self.parents[self.find(x)]
 
    def same(self, x, y): #uf.same(x,y)
        #要素x,yが同じグループに属するかどうかを返す
        return self.find(x) == self.find(y)
 
    def members(self, x): #uf.members(x)
        #要素xが属するグループに属する要素をリストで返す
        root = self.find(x)
        return [i for i in range(self.n) if self.find(i) == root]
 
    def roots(self): #uf.roots()
        #根となっている要素すべてをリストで返す
        return [i for i, x in enumerate(self.parents) if x < 0]
 
    def group_count(self): #uf.group_count()
        #グループの数を返す
        return len(self.roots())
 
    def all_group_members(self): #uf.all_group_members()
        #{ルート要素 : [そのグループに含まれる要素のリスト], ...}のdefaultdictを返す
        group_members = defaultdict(list)
        for member in range(self.n):
            group_members[self.find(member)].append(member)
        return group_members
 
    def __str__(self):
        return '\n'.join(f'{r}: {m}' for r, m in self.all_group_members().items())
 
 
def si(): return input()
#---1つの文字列の受け取り 
def ii(): return int(input())
#---1つの整数の受け取り
def mii(): return map(int, input().split())
#---スペースで区切られた複数の整数の受け取り
def miin(n): return map(lambda x: int(x)+n, input().split())
#---スペースで区切られた複数の整数をそれぞれ+nして受け取り
def lmii(): return list(map(int, input().split()))
#---スペースで区切られた複数の整数をリストで受け取り
def lmiin(n): list(map(lambda x: int(x)+n, input().split()))
#---スペースで区切られた複数の整数をそれぞれ+nしてリストで受け取り
def msi(): return map(str, input())
#---スペースなしの連続した文字列を1文字ずつ受け取り
def msis(): return map(str, input().split())
#---スペースで区切られた複数の文字列の受け取り
def lmsi(): return list(map(str, input()))
#---スペースなしの連続した文字列を1文字ずつリストで受け取り
def yn(): return print("Yes" if ok else "No")
#---変数"ok"がTrueなら「Yes」、Falseなら「No」を出力
 
import string
Upper = list(string.ascii_uppercase) #大文字アルファベットのリスト(["A", "B", "C", ....])
Lower = list(string.ascii_lowercase) #小文字アルファベットのリスト(["a", "b", "c", ....])
Numbers = list(string.digits) #1桁の数字のリスト(["0","1","2", ....])(各要素はstr)

#座標の移動　[0:4]で上下左右4方向、[0:8]で斜めを加えた8方向
dir_x = [0,1,0,-1,1,1,-1,-1]
dir_y = [1,0,-1,0,1,-1,-1,1]

def bin_place(n): #10進数nを2進数に直したときに1になる位のリスト
  k = []
  r = 60
  while n > 2:
    s = 2**r
    if n >= s:
      n -= s
      k.append(r)
    r -= 1
  return k
 
def divisor(x): #整数nの約数をすべて入れたリスト
    List_div = []
    for i in range(1, int(x ** (1 / 2)) + 1):
        if x % i == 0:
            List_div.append(x // i)
            List_div.append(i)
    return sorted(set(List_div))
 
def digit(num): #整数nの桁数
    digits = 1
    while num >= 10:
        num //= 10
        digits += 1
    return digits
 
def pow_x(x,n): #xの0乗～n乗までのリスト
    List_pow = [1]
    for _ in range(n):
        List_pow.append(x * List_pow[-1])
    return List_pow
 
 
#-------------------------------
#-------------------------------

n,a,b,c,d = mii()

if b == c == 0:
    if a*d != 0:
        print("Yes")
    else:
        print("No")

    exit()

print("Yes" if abs(b-c) <= 1 else "No")