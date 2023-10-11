# D - Unicyclic Components
from collections import defaultdict

def main():
  N, M = map(int, input().split())
  lib = UnionFind(N)
  vertexes = set()
  cnt = defaultdict(int)

  for _ in range(M):
    u, v = map(int, input().split())
    lib.unite(u, v)
    vertexes.add(u)
    vertexes.add(v)
    cnt[u] += 1
    cnt[v] += 1
    
  groups = lib.get_groups(vertexes)
  matches = True

  for group in groups:
    n = len(group)
    m = 0

    for v in group:
      m += cnt[v]
    
    if n != m//2:
      matches = False
  
  if matches:
    print('Yes')
  else:
    print('No')


class UnionFind:
  def __init__(self, n):
    # n で頂点数を受け取る
    # 頂点番号が連番ではない場合、最大の頂点番号を渡してもOK
    # 頂点番号は1から始まることが多いので、n頂点の場合、n+1でリストを作成しておく

    # [summary]インスタンス変数を宣言

    # 各頂点の親の番号(自身が根の場合は -1)
    # 最初は、どの頂点も根であるとして初期化
    self.par = [-1] * (n + 1)

    # 各頂点の属するグループの頂点数
    self.size = [1] * (n + 1)

    # 頂点をグループ分けするときに使う
    self.groups = dict()
    
    # サイクル(閉路)を検出するためのフラグ
    self.cycled = False


  def root(self, x):
    # [summary]根を求める
    # その過程で、経路圧縮を行う(par[x]には、根が格納される)

    if self.par[x] == -1:
      # xが根の場合は、直接xを返す
      return x
    else:
      # xの親par[x]を根に張り替える
      # (ここで代入しておくことで、後の繰り返しを避けられる)
      # 各頂点の親子関係を知りたい場合は、この行をコメントアウト
      self.par[x] = self.root(self.par[x])

      return self.root(self.par[x])


  def is_same(self, x, y):
    # [summary]xとyが同じグループに属するかどうか

    if self.root(x) == self.root(y):
      return True


  def unite(self, x, y):
    # [summary]xを含むグループと yを含むグループを併合する

    # x,yを それぞれ根まで移動する
    x = self.root(x)
    y = self.root(y)

    if x == y:
      # すでに同じグループのときは 何もしない
      # すでに同じグループに属しているのに
      # また辺をつないだら、閉路が発生する
      # (実装上は、実際に辺をつなぐ訳ではなく
      # フラグをオンにするだけ)
      self.cycled = True  
    else:
      # union by size(y側のサイズが小さくなるようにする)
      if self.size[x] < self.size[y]:
        x, y = y, x

      # yをxの子にする
      self.par[y] = x
      self.size[x] += self.size[y]


  def detects_cycle(self):
    return self.cycled


  def get_size(self, x):
    # [summary]xを含むグループの頂点数

    return self.size[self.root(x)]


  def get_groups(self, vertexes):
    # [summary]各頂点をグループ分けして、"[1,2,4], [3,5]"のような
    # グループ分けされた頂点番号の集合を返す

    self.__break_into_groups(vertexes)

    return self.groups.values()


  def __break_into_groups(self, vertexes):
    # [summary]各頂点をグループ分けする

    for v in vertexes:
      r = self.root(v)

      if not r in self.groups.keys():
        self.groups[r] = [v]
      else:
        self.groups[r].append(v)


if __name__ == '__main__':
  main()