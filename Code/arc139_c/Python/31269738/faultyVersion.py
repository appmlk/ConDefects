import functools
import math
import decimal
import collections
import itertools
import sys
import random
import bisect
import typing

sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

# Union-Find

class UnionFind():
	def __init__(self, n: int) -> None:
		self.n = n
		self.par = list(range(self.n))
		self.rank = [1] * self.n
		self.count = self.n

	def find(self, x: int) -> int:
		if self.par[x] == x:
			return x
		else:
			self.par[x] = self.find(self.par[x])
			return self.par[x]

	def unite(self, x: int, y: int) -> None:
		p = self.find(x)
		q = self.find(y)
		if p == q:
			return None
		if p > q:
			p, q = q, p
		self.rank[p] += self.rank[q]
		self.par[q] = p
		self.count -= 1

	def same(self, x: int, y: int) -> bool:
		return self.find(x) == self.find(y)

	def size(self, x: int) -> int:
		return self.rank[x]

	def count(self) -> int:
		return self.count

# 素数列挙
def prime_numbers(x: int) -> list:
	if x < 2:
		return []

	prime_numbers = [i for i in range(x)]
	prime_numbers[1] = 0

	for prime_number in prime_numbers:
		if prime_number ** 2 > x:
			break

		if prime_number == 0:
			continue

		for composite_number in range(2 * prime_number, x, prime_number):
			prime_numbers[composite_number] = 0

	return [prime_number for prime_number in prime_numbers if prime_number != 0]
	
# 素数判定
def is_prime(x: int) -> bool:
	if x < 2: return False
	if x == 2 or x == 3 or x == 5: return True
	if x % 2 == 0 or x % 3 == 0 or x % 5 == 0: return False

	prime_number = 7
	difference = 4

	while prime_number ** 2 <= x:
		if x % prime_number == 0: return False

		prime_number += difference
		difference = 6 - difference

	return True

# 素因数分解
def prime_factorize(n: int) -> list:
	res = []

	while n % 2 == 0:
		res.append(2)
		n //= 2

	f = 3

	while f ** 2 <= n:
		if n % f == 0:
			res.append(f)
			n //= f
		else:
			f += 2

	if n != 1:
		res.append(n)

	return res

# nCrなど
mod = 10 ** 9 + 7
class Combinatorics:
	def __init__(self, n: int, mod: int) -> None:
		self.n = n
		self.mod = mod
		self.fa = [1] * (self.n + 1)
		self.fi = [1] * (self.n + 1)

		for i in range(1, self.n + 1):
			self.fa[i] = self.fa[i - 1] * i % self.mod

		self.fi[-1] = pow(self.fa[-1], self.mod - 2, self.mod)

		for i in range(self.n, 0, -1):
			self.fi[i - 1] = self.fi[i] * i % self.mod

	def comb(self, n: int, r: int) -> int:
		if n < r:return 0
		if n < 0 or r < 0:return 0
		return self.fa[n] * self.fi[r] % self.mod * self.fi[n - r] % self.mod

	def perm(self, n: int, r: int) -> int:
		if n < r:return 0
		if n < 0 or r < 0:return 0
		return self.fa[n] * self.fi[n - r] % self.mod
		
	def combr(self, n: int, r: int) -> int:
		if n == r == 0:return 1
		return self.comb(n + r - 1, r)


# BIT
class BinaryIndexedTree():
	def __init__(self, n: int) -> None:
		self.n = 1 << (n.bit_length())
		self.BIT = [0] * (self.n + 1)

	def build(self, init_lis: list) -> None:
		for i, v in enumerate(init_lis):
			self.add(i, v)

	def add(self, i: int, x: int) -> None:
		i += 1
		while i <= self.n:
			self.BIT[i] += x
			i += i & -i
	
	def sum(self, l: int, r: int) -> int:
		return self._sum(r) - self._sum(l)

	def _sum(self, i: int) -> int:
		res = 0
		while i > 0:
			res += self.BIT[i]
			i -= i & -i
		return res

	def binary_search(self, x: int) -> int:
		i = self.n
		while True:
			if i & 1:
				if x > self.BIT[i]:
					i += 1
				break
			if x > self.BIT[i]:
				x -= self.BIT[i]
				i += (i & -i) >> 1
			else:
				i -= (i & -i) >> 1
		return i

# Floor-Sum
def floor_sum(n: int, m: int, a: int, b: int) -> int:
	res = 0
	if a >= m:
		res += (n - 1) * n * (a // m) // 2
		a %= m
	if b >= m:
		res += n * (b // m)
		b %= m
	y_max = (a * n + b) // m
	x_max = y_max * m - b
	if y_max == 0:
		return res
	res += y_max * (n + (-x_max // a))
	res += floor_sum(y_max, a, m, (a - x_max % a) % a)
	return res

# Z-Algorithm
def z_algorithm(s: str) -> list:
	str_len = len(s)
	res = [0] * str_len
	res[str_len - 1] = str_len
	i, j = 1, 0
	while i < str_len:
		while i + j < str_len and s[i + j] == s[j]:
			j += 1
		res[i] = j
		if j == 0:
			i += 1
			continue
		k = 1
		while i + k < str_len and j > res[k] + k:
			res[i + k] = res[k]
			k += 1
		i += k
		j -= k
	return res

# Manacher's Algorithm
class Manacher():
	def __init__(self, s: str) -> None:
		self.s = s
	def coustruct(self) -> list:
		i, j = 0, 0
		res = [0] * len(self.s)
		while i < len(self.s):
			while i - j >= 0 and i + j < len(self.s) and self.s[i - j] == self.s[i + j]:
				j += 1
			res[i] = j
			k = 1
			while i - k >= 0 and i + k < len(self.s) and k + res[i - k] < j:
				res[i + k] = res[i - k]
				k += 1
			i += k
			j -= k
		return res


# mod-sqrt
def mod_sqrt(a: int, p: int) -> int:
	if a == 0: return 0
	if p == 2: return 1
	k = (p - 1) // 2
	if pow(a, k, p) != 1: return -1
	while True:
		n = random.randint(2, p - 1)
		r = (n ** 2 - a) % p
		if r == 0: return n
		if pow(r, k, p) == p - 1: break
	k += 1
	w, x, y, z = n, 1, 1, 0
	while k:
		if k % 2:
			y, z = w * y + r * x * z, x * y + w * z
		w, x = w * w + r * x * x, 2 * w * x
		w %= p; x %= p; y %= p; z %= p
		k >>= 1
	return y

import typing

import typing

# Segment Tree
class SegmentTree:
	def __init__(
			self, 
			lis: list, 
			ele: typing.Any, 
			op: typing.Callable[[typing.Any, typing.Any], typing.Any]) -> None:
		self.n = len(lis)
		self.log = (self.n - 1).bit_length()
		self.size = 1 << self.log
		self.op = op
		self.ele = ele
		self.tree = self._build(lis)

	def _build(self, lis: list) -> list:
		res_tree = [self.ele] * (2 * self.size)
		for i, a in enumerate(lis):
			res_tree[self.size + i] = a
		for i in range(1, self.size)[::-1]:
			res_tree[i] = self.op(res_tree[2 * i], res_tree[2 * i + 1])
		return res_tree

	def __getitem__(self, i: int) -> None:
		return self.tree[self.size + i]

	def __setitem__(self, p: int, x: int) -> None:
		p += self.size
		self.tree[p] = x
		for i in range(1, self.log + 1):
			self.tree[p >> i] = self.op(self.tree[2 * (p >> i)], self.tree[2 * (p >> i) + 1])

	def prod(self, l: int, r: int) -> typing.Any:
		l += self.size
		r += self.size
		L = R = self.ele
		while l < r:
			if l & 1:
				L = self.op(L, self.tree[l])
				l += 1
			if r & 1:
				r -= 1
				R = self.op(self.tree[r], R)
			l >>= 1
			r >>= 1
		return self.op(L, R)

	def all_prod(self) -> typing.Any:
		return self.tree[1]

	def max_right(self, l: int, f: typing.Callable[[typing.Any], typing.Any]) -> int:
		if l == self.n:
			return self.n
		l += self.size
		sm = self.ele
		while True:
			while l % 2 == 0:
				l >>= 1
			if not f(self.op(sm, self.tree[l])):
				while l < self.size:
					l *= 2
					if f(self.op(sm, self.tree[l])):
						sm = self.op(sm, self.tree[l])
						l += 1
				return l - self.size
			sm = self.op(sm, self.tree[l])
			l += 1
			if (l & -l) == l:
				return self.n

	def min_left(self, r: int, f: typing.Callable[[typing.Any], typing.Any]) -> int:
		if r == 0:
			return 0
		r += self.size
		sm = self.ele
		while True:
			r -= 1
			while r > 1 and (r % 2):
				r >>= 1
			if not f(self.op(self.tree[r], sm)):
				while r < self.size:
					r = 2 * r + 1
					if f(self.op(self.tree[r], sm)):
						sm = self.op(self.tree[r], sm)
						r -= 1
				return r + 1 - self.size
			sm = self.op(self.d[r], sm)
			if (r & -r) == r:
				return 0

# 1次元座標圧縮
def one_d_coordinate_compression(l: list) -> list:
	n = len(l)
	sorted_list = sorted(set(l))
	d = {sorted_list[i]: i for i in range(len(sorted_list))}
	return [d[i] for i in l]

# 重み付きUnion-Find
class WeightedUnionFind:
	def __init__(self, n: int) -> None:
		self.n = n
		self.par = list(range(n))
		self.rank = [0] * n
		self.weight = [0] * n

	def find(self, x: int) -> int:
		if self.par[x] == x:
			return x
		else:
			y = self.find(self.par[x])
			self.weight[x] += self.weight[self.par[x]]
			self.par[x] = y
			return y

	def unite(self, x: int, y: int, w: int) -> None:
		p, q = self.find(x), self.find(y)
		if self.rank[p] < self.rank[q]:
			self.par[p] = q
			self.weight[p] = w - self.weight[x] + self.weight[y]
		else:
			self.par[q] = p
			self.weight[q] = -w - self.weight[y] + self.weight[x]
			if self.rank[p] == self.rank[q]:
				self.rank[p] += 1

	def same(self, x: int, y: int) -> bool:
		return self.find(x) == self.find(y)

	def diff(self, x: int, y: int) -> int:
		return self.weight[x] - self.weight[y]

# Trie
class TrieNode:
	def __init__(self):
		self.child = collections.defaultdict(TrieNode)
		self.is_word = False

class Trie:
	def __init__(self):
		self.root = TrieNode()

	def insert(self, word: str) -> None:
		cur = self.root
		for letter in word:
			cur = cur.child[letter]
		cur.is_word = True

	def search(self, word: str) -> bool:
		cur = self.root
		for letter in word:
			cur = cur.child.get(letter)
			if cur == None:
				return False
		return cur.is_word

	def starts_with(self, prefix: str) -> bool:
		cur = self.root
		for letter in prefix:
			cur = cur.child.get(letter)
			if cur == None:
				return False
		return True

import bisect

# 部分永続Union-Find
class PartiallyPersistentUnionFind:
	def __init__(self, n: int) -> None:
		self.par = list(range(n))
		self.size = [1] * n
		self.h = [1] * n
		self.s = [[(0, 1)] for i in range(n)]
		self.t = [10 ** 18] * n

	def find(self, x: int, t: int) -> int:
		while self.t[x] <= t:
			x = self.par[x]
		return x

	def unite(self, x: int, y: int, t: int) -> None:
		p = self.find(x, t)
		q = self.find(y, t)
		if p == q:
			return None
		if self.h[q] < self.h[p]:
			self.par[q] = p
			self.t[q] = t
			self.size[p] += self.size[q]
			self.s[p].append((t, self.size[p]))
		else:
			self.par[p] = q
			self.t[p] = t
			self.size[q] += self.size[p]
			self.s[q].append((t, self.size[q]))
			self.h[q] = max(self.h[q], self.h[p] + 1)

	def getsize(self, x: int, t: int = 10 ** 9) -> int:
		p = self.find(x, t)
		ind = bisect.bisect(self.s[p], (t, 10 ** 18)) - 1
		return self.s[p][ind][1]

	def same(self, x: int, y: int, t: int = 10 ** 9) -> int:
		return self.find(x, t) == self.find(y, t)

	def binary_search(self, x: int, y: int) -> int:
		if not self.same(x, y):
			return -1
		l, r = 0, 10 ** 9
		ans = 10 ** 18
		while l < r:
			m = (l + r) // 2
			if self.same(x, y, m):
				ans = min(ans, m)
				r = m
			else:
				l = m
		return ans + 1

# 遅延セグ木

class LazySegmentTree:
	def __init__(
			self, 
			lis: list, 
			op: typing.Callable[[typing.Any, typing.Any], typing.Any], 
			ele: typing.Any, 
			mapp: typing.Callable[[typing.Any, typing.Any], typing.Any], 
			comp: typing.Callable[[typing.Any, typing.Any], typing.Any], 
			id: typing.Any) -> None:
		self.lis = lis
		self.n = len(lis)
		self.op = op
		self.ele = ele
		self.mapp = mapp
		self.comp = comp
		self.id = id
		self.log = (self.n - 1).bit_length()
		self.size = 1 << self.log
		self.data = [ele] * (2 * self.size)
		self.lazy = [id] * self.size
		self._build(lis)

	def update(self, k: int) -> None:
		self.data[k] = self.op(self.data[2 * k], self.data[2 * k + 1])

	def _build(self, lis: list) -> None:
		for i, l in enumerate(lis, self.size):
			self.data[i] = l
		for i in range(1, self.size)[::-1]:
			self.update(i)

	def __setitem__(self, p: int, x: int) -> None:
		p += self.size
		for i in range(1, self.log + 1)[::-1]:
			self.push(p >> i)
		self.data[p] = x
		for i in range(1, self.log + 1):
			self.update(p >> i)

	def __getitem__(self, p: int) -> typing.Any:
		p += self.size
		for i in range(1, self.log + 1):
			self.push(p >> i)
		return self.data[p]

	def apply(self, p: int, f: typing.Optional[typing.Any]) -> None:
		p += self.size
		for i in range(1, self.log + 1)[::-1]:
			self.push(p >> i)
		self.data[p] = self.mapp(f, self.data[p])
		for i in range(1, self.log + 1):
			self.update(p >> i)

	def range_apply(self, l: int, r: int, f: typing.Optional[typing.Any]) -> None:
		if l == r: return 
		l += self.size
		r += self.size
		for i in range(1, self.log + 1)[::-1]:
			if ((l >> i) << i) != l:
				self.push(l >> i)
			if ((r >> i) << i) != r:
				self.push((r - 1) >> i)
		l2, r2 = l, r
		while l2 < r2:
			if l2 & 1:
				self.all_apply(l2, f)
				l2 += 1
			if r2 & 1:
				r2 -= 1
				self.all_apply(r2, f)
			l2 >>= 1
			r2 >>= 1
		for i in range(1, self.log + 1):
			if ((l >> i) << i) != l:
				self.update(l >> i)
			if ((r >> i) << i) != r:
				self.update((r - 1) >> i)

	def all_apply(self, k: int, f: typing.Optional[typing.Any]) -> None:
		self.data[k] = self.mapp(f, self.data[k])
		if k < self.size:
			self.lazy[k] = self.comp(f, self.lazy[k])

	def push(self, k: int) -> None:
		self.all_apply(2 * k, self.lazy[k])
		self.all_apply(2 * k + 1, self.lazy[k])
		self.lazy[k] = self.id

	def prod(self, l: int, r: int) -> typing.Any:
		if l == r: return self.ele
		l += self.size
		r += self.size
		for i in range(1, self.log + 1)[::-1]:
			if ((l >> i) << i) != l:
				self.push(l >> i)
			if ((r >> i) << i) != r:
				self.push(r >> i)
		sml = smr = self.ele
		while l < r:
			if l & 1:
				sml = self.op(sml, self.data[l])
				l += 1
			if r & 1:
				r -= 1
				smr = self.op(self.data[r], smr)
			l >>= 1
			r >>= 1
		return self.op(sml, smr)

	def all_prod(self) -> typing.Any:
		return self.data[1]

	def max_right(self, l: int, g: typing.Callable[[typing.Any], bool]) -> int:
		if l == self.n: return self.n
		l += self.size
		for i in range(1, self.log + 1)[::-1]:
			self.push(l >> i)
		sm = self.ele
		while True:
			while l % 2 == 0:
				l >>= 1
			if not g(self.op(sm, self.data[l])):
				while l < self.size:
					self.push(l)
					l <<= 1
					if g(self.op(sm, self.data[l])):
						sm = self.op(sm, self.data[l])
						l += 1
				return l - self.size
			sm = self.op(sm, self.data[l])
			l += 1
			if (l & -l) == l: return self.n

	def min_left(self, r: int, g: typing.Callable[[typing.Any], bool]) -> int:
		if r == 0: return 0
		r += self.size
		for i in range(1, self.log + 1)[::-1]:
			self.push((r - 1) >> i)
		sm = self.ele
		while True:
			r -= 1
			while r > 1 and r % 2:
				r >>= 1
			if not g(self.op(self.data[r], sm)):
				while r < self.size:
					self.push(r)
					r = 2 * r + 1
					if g(self.op(self.data[r], sm)):
						sm = self.op(self.data[r], sm)
						r -= 1
				return r + 1 - self.size
			sm = self.op(self.data[r], sm)
			if (r & -r) == r: return 0

# SCC
def strongly_connected_components(G: list) -> list:
	n = len(G)
	G_rev = [[] for i in range(n)]
	for i in range(n):
		for v in G[i]:
			G_rev[v].append(i)
	vs = []
	visited = [False] * n
	def dfs(v):
		visited[v] = True
		for u in G[v]:
			if not visited[u]:
				dfs(u)
		vs.append(v)
	for i in range(n):
		if not visited[i]:
			dfs(i)
	rev_visited = [False] * n
	def rev_dfs(v):
		p.append(v)
		rev_visited[v] = True
		for u in G_rev[v]:
			if not rev_visited[u]:
				rev_dfs(u)
	res = []
	for v in vs[::-1]:
		if not rev_visited[v]:
			p = []
			rev_dfs(v)
			res.append(p)
	return res

class TwoSat:
	def __init__(self, n) -> None:
		self.n = n
		self.res = [0] * self.n
		self.G = [[] for i in range(2 * self.n)]

	def add_clause(self, i, f, j, g) -> None:
		self.G[2 * i + (not f)].append(2 * j + g)
		self.G[2 * j + g].append(2 * i + (not f))
		self.G[2 * j + (not g)].append(2 * i + f)
		self.G[2 * i + f].append(2 * j + (not g))

	def satisfiabe(self) -> bool:
		scc = strongly_connected_components(self.G)
		l = [0] * (2 * self.n)
		for i in range(2 * self.n):
			for j in scc[i]:
				l[j] = i
		for i in range(self.n):
			if l[2 * i] == l[2 * i +  1]: return False
			self.res[i] = (l[2 * i] < l[2 * i + 1])
		return True

	def result(self):
		return self.res

# SkewHeap(遅延伝播)
class SHNode:
	def __init__(self, val: int) -> None:
		self.left = None
		self.right = None
		self.val = val
		self.add = 0

	def lazy(self) -> None:
		if self.left != None: self.left.add += self.add
		if self.right != None: self.right.add += self.add
		self.val += self.add
		self.add = 0

class SkewHeap:
	def __init__(self) -> None:
		self.root = None

	def heapmeld(self, h1: SHNode, h2: SHNode) -> SHNode:
		if h1 == None: return h2
		if h2 == None: return h1
		if h1.val + h1.add > h2.val + h2.add:
			h1, h2 = h2, h1
		h1.lazy()
		h1.right = self.heapmeld(h2, h1.right)
		h1.left, h1.right = h1.right, h1.left
		return h1

	def heappop(self) -> int:
		res = self.root
		res.lazy()
		self.root = self.heapmeld(res.left, res.right)
		return res.val

	def heappush(self, x: int) -> None:
		nh = SHNode(x)
		self.root = self.heapmeld(self.root, nh)

	def heaptop(self) -> typing.Union[int, None]:
		if self.root == None: return None
		return self.root.val

	def heapadd(self, val: int) -> None:
		self.root.add += val


# 最小全域有向木
def directed_minimum_spanning_tree(n: int, m: int, edges: list, root: int) -> typing.Tuple[int, list]:
	froms = [0] * n
	from_cost = [0] * n
	from_heap = [SkewHeap() for i in range(n)]
	UF = UnionFind(n)
	par = [-1] * m
	stem = [-1] * n
	used = [0] * n
	used[root] = 2
	inds = []
	for i, (u, v, c) in enumerate(edges):
		from_heap[v].heappush(c * m + i)
	res = 0
	for v in range(n):
		if used[v] != 0: continue
		proc = []
		chi = []
		cycle = 0
		while used[v] != 2:
			used[v] = 1
			proc.append(v)
			if from_heap[v].root == None: return -1, [-1] * n
			from_cost[v], ind = divmod(from_heap[v].heappop(), m)
			froms[v] = UF.find(edges[ind][0])
			if stem[v] == -1: stem[v] = ind
			if froms[v] == v: continue
			res += from_cost[v]
			inds.append(ind)
			while cycle:
				par[chi.pop()] = ind
				cycle -= 1
			chi.append(ind)
			if used[froms[v]] == 1:
				p = v
				while True:
					if not from_heap[p].root == None:
						from_heap[p].heapadd(-from_cost[p] * m)
					if p != v:
						UF.unite(v, p)
						from_heap[v].root = from_heap[v].heapmeld(from_heap[v].root, from_heap[p].root)
					p = UF.find(froms[p])
					new_v = UF.find(v)
					from_heap[new_v] = from_heap[v]
					v = new_v
					cycle += 1
					if p == v: break
			else:
				v = froms[v]
		for v in proc:
			used[v] = 2
	visited = [0] * m
	tree = [-1] * n
	for i in inds[::-1]:
		if visited[i]: continue
		u, v, c = edges[i]
		tree[v] = u
		x = stem[v]
		while x != i:
			visited[x] = 1
			x = par[x]
	return res, tree

	
# 永続配列
class PersistentArrayNode:
	def __init__(self, log: int) -> None:
		self.val = None
		self.ch = [None] * (1 << log)

class PersistentArray:
	def __init__(self, log: int = 4) -> None:
		self.log = log
		self.mask = (1 << log) - 1

	def build(self, array: list) -> typing.Union[PersistentArrayNode, None]:
		rt = None
		for i, val in enumerate(array):
			rt = self.init_set(i, val, rt)
		return rt

	def init_set(self, i: int, val: typing.Any, t: typing.Union[PersistentArrayNode, None]) -> PersistentArrayNode:
		if t is None:
			t = PersistentArrayNode(self.log)
		if i == 0:
			t.val = val
		else:
			t.ch[i & self.mask] = self.init_set(i >> self.log, val, t.ch[i & self.mask])
		return t

	def set(self, i: int, val: typing.Any, t: int) -> PersistentArrayNode:
		res = PersistentArrayNode(self.log)
		if t is not None:
			res.ch = t.ch[:]
			res.val = t.val
		if i == 0:
			res.val = val
		else:
			res.ch[i & self.mask] = self.set(i >> self.log, val, res.ch[i & self.mask])
		return res
		
	def get(self, i: int, t: int) -> typing.Any:
		if i == 0:
			return t.val
		else:
			return self.get(i >> self.log, t.ch[i & self.mask])

# 完全永続Union-Find
class PersistentUnionFind:
	def __init__(self, n: int) -> None:
		self.par = PersistentArray()
		self.rt = self.par.build([-1] * n)
	
	def find(self, x: int, t: int) -> None:
		p = self.par.get(x, t)
		if p < 0: return x
		return self.find(p, t)

	def unite(self, x: int, y: int, t: int) -> int:
		p = self.find(x, t)
		q = self.find(y, t)
		if x == y: return t
		px = self.par.get(p, t)
		qy = self.par.get(q, t)
		if px > qy:
			p, q = q, p
		tmp = self.par.set(q, p, t)
		return self.par.set(p, px + qy, tmp)

# Li-Chao-Tree
class LiChaoTree:
	def __init__(self, x_list: list, INF: int = 10 ** 18) -> None:
		x_list = sorted(list(set(x_list)))
		self.comp = {x : k for k, x in enumerate(x_list)}
		self.log = (len(x_list) - 1).bit_length()
		self.n = 1 << self.log
		self.ele = (0, INF)
		self.xs = x_list + [INF] * (self.n - len(x_list))
		self.inf = INF
		self.tree = [self.ele for _ in range(2 * self.n)]

	def f(self, line: typing.Tuple[int, int], x: int) -> int:
		a, b = line
		return a * x + b

	def _add_(self, line: typing.Tuple[int, int], ind: int, left: int, right: int) -> None:
		while True:
			mid = (left + right) // 2
			lx = self.xs[left]
			mx = self.xs[mid]
			rx = self.xs[right - 1]
			lu = self.f(line, lx) < self.f(self.tree[ind], lx)
			mu = self.f(line, mx) < self.f(self.tree[ind], mx)
			ru = self.f(line, rx) < self.f(self.tree[ind], rx)
			if lu and ru:
				self.tree[ind] = line
				return
			if not lu and not ru:
				return
			if mu:
				self.tree[ind], line = line, self.tree[ind]
			if lu != mu:
				right = mid
				ind = ind * 2
			else:
				left = mid
				ind = ind * 2 + 1

	def add_line(self, line: typing.Tuple[int, int]) -> None:
		self._add_(line, 1, 0, self.n)

	def add_segment(self, line: typing.Tuple[int, int], left: int, right: int) -> None:
		lind, rind = self.comp[left] + self.n, self.comp[right] + self.n
		left, right = self.comp[left], self.comp[right]
		size = 1
		while lind < rind:
			if lind & 1:
				self._add_(line, lind, left, left + size)
				lind += 1
				left += size
			if rind & 1:
				rind -= 1
				right -= size
				self._add_(line, rind, right, right + size)
			lind >>= 1
			rind >>= 1
			size <<= 1

	def get_min(self, x: int) -> int:
		ind = self.comp[x] + self.n
		res = self.inf
		while ind:
			res = min(res, self.f(self.tree[ind], x))
			ind >>= 1
		return res

# k 乗根の切り捨て/切り上げ
def root_floor(n: int, k: int) -> int:
	l, r = 0, int(pow(n, 1 / k)) + 10000
	while r - l > 1:
		m = (l + r) // 2
		if pow(m, k) > n:
			r = m
		else:
			l = m
	return l

def root_ceil(n: int, k: int) -> int:
	l, r = 0, int(pow(n, 1 / k)) + 10000
	while r - l > 1:
		m = (l + r) // 2
		if pow(m, k) < n:
			l = m
		else:
			r = m
	return r

# Baby-Step Giant-Step
def discrete_logarithm(x: int, y: int, m: int) -> int:
	if m == 1: return 0
	if y == 1: return 0
	if x == 0:
		if y == 0: return 1
		else: return -1
	sq = root_ceil(m, 2) + 1
	d = dict()
	z = 1
	for i in range(sq):
		if z % m == y: return i
		d[y * z % m] = i
		z *= x
		z %= m
	g = pow(x, sq, m)
	z = g
	for i in range(1, sq + 1):
		if z in d:
			num = d[z]
			res = i * sq - num
			return res if pow(x, res, m) == y else -1
		z *= g
		z %= m
	return -1

# NTT
class NumberTheoreticTransform:
	def primitive_root(self, m: int) -> int:
		if m == 2: return 1
		if m == 167772161: return 3
		if m == 469762049: return 3
		if m == 754974721: return 11
		if m == 998244353: return 3
		divs = [0] * 20
		divs[0] = 2
		cnt = 1
		x = (m - 1) // 2
		while x % 2 == 0: x //= 2
		i = 3
		while i ** 2 <= x:
			if x % i == 0:
				divs[cnt] = i
				cnt += 1
				while x % i == 0: x //= i
		if x > 1:
			divs[cnt] = x
			cnt += 1
		g = 2
		while True:
			f = True
			for i in range(cnt):
				if pow(g, (m - 1) // divs[i], m) == 1: break
			else:
				return g
			g += 1

	def bsf(self, x: int) -> int:
		res = 0
		while x % 2 == 0:
			res += 1
			x //= 2
		return res

	def __init__(self, mod: int = 998244353) -> None:
		self.mod = mod
		self.g = self.primitive_root(self.mod)

	def butterfly(self, a: list) -> None:
		n = len(a)
		h = (n - 1).bit_length()
		sum_e = [0] * 30
		first = True
		if first:
			first = False
			es = [0] * 30
			ies = [0] * 30
			cnt2 = self.bsf(self.mod - 1)
			e = pow(self.g, (self.mod - 1) >> cnt2, self.mod)
			ie = invmod(e, self.mod)
			for i in range(cnt2, 1, -1):
				es[i - 2] = e
				ies[i - 2] = ie
				e = e ** 2 % self.mod
				ie = ie ** 2 % self.mod
			now = 1
			for i in range(cnt2 - 2):
				sum_e[i] = es[i] * now % self.mod
				now = now * ies[i] % self.mod
		for ph in range(1, h + 1):
			w = 1 << (ph - 1)
			p = 1 << (h - ph)
			now = 1
			for s in range(w):
				offset = s << (h - ph + 1)
				for i in range(p):
					l = a[i + offset]
					r = a[i + offset + p] * now % self.mod
					a[i + offset] = (l + r) % self.mod
					a[i + offset + p] = (l - r) % self.mod
				now = now * sum_e[(~s & -~s).bit_length() - 1] % self.mod

	def butterfly_inv(self, a: list) -> None:
		n = len(a)
		h = (n - 1).bit_length()
		sum_ie = [0] * 30
		first = True
		if first:
			first = False
			es = [0] * 30
			ies = [0] * 30
			cnt2 = self.bsf(self.mod - 1)
			e = pow(self.g, (self.mod - 1) >> cnt2, self.mod)
			ie = invmod(e, self.mod)
			for i in range(cnt2, 1, -1):
				es[i - 2] = e
				ies[i - 2] = ie
				e = e ** 2 % self.mod
				ie = ie ** 2 % self.mod
			now = 1
			for i in range(cnt2 - 2):
				sum_ie[i] = ies[i] * now % self.mod
				now = now * es[i] % self.mod
		for ph in range(h, 0, -1):
			w = 1 << (ph - 1)
			p = 1 << (h - ph)
			inow = 1
			for s in range(w):
				offset = s << (h - ph + 1)
				for i in range(p):
					l = a[i + offset]
					r = a[i + offset + p]
					a[i + offset] = (l + r) % self.mod
					a[i + offset + p] = (l - r) * inow % self.mod
				inow = inow * sum_ie[(~s & -~s).bit_length() - 1] % self.mod

	def convolution(self, a: list, b: list) -> list:
		n = len(a)
		m = len(b)
		if not a or not b: return []
		z = 1 << ((n + m - 2).bit_length())
		a += [0] * (z - n)
		b += [0] * (z - m)
		self.butterfly(a)
		self.butterfly(b)
		c = [0] * z
		for i in range(z):
			c[i] = (a[i] * b[i]) % self.mod
		self.butterfly_inv(c)
		iz = invmod(z, self.mod)
		for i in range(n + m - 1):
			c[i] = c[i] * iz % self.mod
		return c[:n + m - 1]
	
# 形式的冪級数（未完成）
class FormalPowerSeries:
	def __init__(self, n: int, l: list = [], mod: int = 998244353) -> None:
		self.n = n
		self.l = l + [0] * (n - len(l))
		self.mod = mod
	
	def __add__(self, other):
		res = FormalPowerSeries(self.n, [], self.mod)
		for i in range(self.n):
			res.l[i] = self.l[i] + other.l[i]
			res.l[i] %= self.mod
		return res
	
	def __sub__(self, other):
		res = FormalPowerSeries(self.n, [], self.mod)
		for i in range(self.n):
			res.l[i] = self.l[i] - other.l[i]
			res.l[i] %= self.mod
		return res

	def __mul__(self, other):
		res = FormalPowerSeries(self.n, [], self.mod)
		NTT = NumberTheoreticTransform(self.mod)
		cv = NTT.convolution(self.l, other.l)
		for i in range(self.n):
			res.l[i] = cv[i]
		return res
	
	def resize(self, n: int):
		res = FormalPowerSeries(n, [], self.mod)
		for i in range(min(n, self.n)):
			res.l[i] = self.l[i]
		return res

	def times(self, k: int):
		res = FormalPowerSeries(self.n, [], self.mod)
		for i in range(self.n):
			res.l[i] = self.l[i] * k % self.mod
		return res

	def inverse(self):
		r = invmod(self.l[0], self.mod)
		m = 1
		res = FormalPowerSeries(m, [r], self.mod)
		while m < self.n:
			m *= 2
			res = res.resize(m)
			res = res.times(2).subtract(res.multiply(res.resize(m)).multiply(self.resize(m)))
		res = res.resize(self.n)
		return res

	def divide(self, other) -> None:
		self.multiply(self, other.inverse())

	def differentiate(self):
		res = FormalPowerSeries(self.n, [], self.mod)
		for i in range(1, self.n):
			res.l[i - 1] = self.l[i] * i % self.mod
		return res

	def integrate(self):
		res = FormalPowerSeries(self.n, [], self.mod)
		for i in range(self.n - 1):
			res.l[i + 1] = self.l[i] * invmod(i + 1, self.mod)
		return res

# BitVector 
class BitVector:
	def __init__(self, size: int) -> None:
		self.block = (size + 31) >> 5
		self.bit = [0] * self.block
		self.cnt = [0] * self.block
	
	def set(self, i: int) -> None:
		self.bit[i >> 5] |= 1 << (i & 31)

	def build(self) -> None:
		for i in range(self.block - 1):
			self.cnt[i + 1] = self.cnt[i] + self.popcount(self.bit[i])

	def popcount(self, x: int) -> int:
		x = x - ((x >> 1) & 0x55555555)
		x = (x & 0x33333333) + ((x >> 2) & 0x33333333)
		x = (x + (x >> 4)) & 0x0f0f0f0f
		x = x + (x >> 8)
		x = x + (x >> 16)
		return x & 0x0000007f
	
	def rank1(self, r: int) -> int:
		msk = (1 << (r & 31)) - 1
		return self.cnt[r >> 5] + self.popcount(self.bit[r >> 5] & msk)

	def rank0(self, r: int) -> int:
		return r - self.rank1(r)

# Wavelet Matrix
class WaveletMatrix:
	def __init__(self, array: list, log: int = 32) -> None:
		self.n = len(array)
		self.mat = []
		self.zs = []
		self.log = log
		for d in range(self.log)[::-1]:
			ls, rs = [], []
			BV = BitVector(self.n + 1)
			for ind, val in enumerate(array):
				if val & (1 << d):
					rs.append(val)
					BV.set(ind)
				else:
					ls.append(val)
			BV.build()
			self.mat.append(BV)
			self.zs.append(len(ls))
			array = ls + rs
	
	def access(self, i: int) -> int:
		res = 0
		for d in range(self.log):
			res <<= 1
			if self.mat[d][i]:
				res |= 1
				i = self.mat[d].rank1(i) + self.zs[d]
			else:
				i = self.mat[d].rank0(i)
		return res

	def rank(self, val: int, l: int, r: int) -> int:
		for d in range(self.log):
			if val >> (self.log - d - 1) & 1:
				l = self.mat[d].rank1(l) + self.zs[d]
				r = self.mat[d].rank1(r) + self.zs[d]
			else:
				l = self.mat[d].rank0(l)
				r = self.mat[d].rank0(r)
		return r - l
	
	def quantile(self, l: int, r: int, k: int) -> int:
		res = 0
		for d in range(self.log):
			res <<= 1
			cntl, cntr = self.mat[d].rank1(l), self.mat[d].rank1(r)
			if cntr - cntl >= k:
				l = cntl + self.zs[d]
				r = cntr + self.zs[d]
				res |= 1
			else:
				l -= cntl
				r -= cntr
				k -= cntr - cntl
		return res

	def kth_smallest(self, l: int, r: int, k: int) -> int:
		return self.quantile(l, r, r - l - k)

class CompressedWaveletMatrix:
	def __init__(self, array: list) -> None:
		self.array = sorted(set(array))
		self.comp = {val: ind for ind, val in enumerate(self.array)}
		array = [self.comp[val] for val in array]
		log = len(self.array).bit_length()
		self.WM = WaveletMatrix(array, log)

	def access(self, i: int) -> int:
		return self.array[self.WM.access(i)]

	def rank(self, l: int, r: int, val: int) -> int:
		if val not in self.comp: return 0
		return self.WM.rank(self.comp[val], l, r)
	
	def kth_smallest(self, l: int, r: int, k: int) -> int:
		return self.array[self.WM.kth_smallest(l, r, k)]

# 中国剰余定理
def invgcd(a: int, b: int) -> typing.Tuple[int, int]:
	a %= b
	if a == 0: return (b, 0)
	s, t, m0, m1 = b, a, 0, 1
	while t:
		u = s // t
		s %= t
		m0 -= m1 * u
		s, t = t, s
		m0, m1 = m1, m0
	if m0 < 0: m0 += b // s
	return (s, m0)

def garner(r: list, m: list) -> typing.Tuple[int, int]:
	n = len(r)
	r0, m0 = 0, 1
	for i in range(n):
		r1, m1 = r[i] % m[i], m[i]
		if m0 < m1:
			r0, r1 = r1, r0
			m0, m1 = m1, m0
		if m0 % m1 == 0:
			if r0 % m1 != r1: return (0, 0)
			continue
		g, im = invgcd(m0, m1)
		u1 = m1 // g
		if (r1 - r0) % g: return (0, 0)
		x = (r1 - r0) // g % u1 * im % u1
		r0 += x * m0
		m0 *= u1
		if r0 < 0: r0 += m0
	return (r0, m0)

# Bitwise And Convolution
class BitwiseAndConvolution:
	def __init__(self, mod: int = 998244353) -> None:
		self.mod = mod

	def fast_zeta_transform_and(self, a: list) -> list:
		n = len(a)
		b = (n - 1).bit_length()
		for i in range(b):
			bit = 1 << i
			for j in range(n):
				if not bit & j:
					a[j] += a[bit | j]
					a[j] %= self.mod
		return a

	def fast_mobius_transform_and(self, a: list) -> list:
		n = len(a)
		b = (n - 1).bit_length()
		for i in range(b):
			bit = 1 << i
			for j in range(n):
				if not bit & j:
					a[j] -= a[bit | j]
					a[j] %= self.mod
		return a

	def bitwise_and_convolution(self, a: list, b: list) -> list:
		n = len(a)
		A = self.fast_zeta_transform_and(a)
		B = self.fast_zeta_transform_and(b)
		C = [i * j % self.mod for i, j in zip(A, B)]
		return self.fast_mobius_transform_and(C)

# Bitwise Or Convolution
class BitwiseOrConvolution:
	def __init__(self, mod: int = 9888244353) -> None:
		self.mod = mod

	def fast_zeta_transform_or(self, a: list) -> list:
		n = len(a)
		b = (n - 1).bit_length()
		for i in range(b):
			bit = 1 << i
			for j in range(n):
				if bit & j:
					a[j] += a[bit ^ j]
					a[j] %= self.mod
		return a

	def fast_mobius_transform_or(self, a: list) -> list:
		n = len(a)
		b = (n - 1).bit_length()
		for i in range(b):
			bit = 1 << i
			for j in range(n):
				if bit & j:
					a[j] -= a[bit ^ j]
					a[j] %= self.mod
		return a

	def bitwise_or_convolution(self, a: list, b: list) -> list:
		n = len(a)
		A = self.fast_zeta_transform_or(a)
		B = self.fast_zeta_transform_or(b)
		C = [i * j % self.mod for i, j in zip(A, B)]
		return self.fast_mobius_transform_or(C)

# Bitwise Xor Convolution
class BitwiseXorConvolution:
	def __init__(self, mod: int = 998244353) -> None:
		self.mod = mod

	def fast_hadamard_transform(self, a: list) -> list:
		n = len(a)
		b = (n - 1).bit_length()
		for i in range(b):
			bit = 1 << i
			for j in range(n):
				if not bit & j:
					x, y = a[j], a[j | bit]
					a[j] = (x + y) % self.mod
					a[j | bit] = (x - y) % self.mod
		return a

	def inv_fast_hadamard_transform(self, a: list) -> list:
		a = self.fast_hadamard_transform(a)
		n = len(a)
		inv = invmod(n, self.mod)
		for i in range(n):
			a[i] *= inv
			a[i] %= self.mod
		return a

	def bitwise_xor_convolution(self, a: list, b: list) -> list:
		A = self.fast_hadamard_transform(a)
		B = self.fast_hadamard_transform(b)
		n = len(a)
		C = [i * j for i, j in zip(A, B)]
		C = self.inv_fast_hadamard_transform(C)
		return C

# HLD
class HeavyLightDecomposition:
	def __init__(self, G: list) -> None:
		self.G = G
		self.n = len(G)
		self.par = [-1] * self.n
		self.size = [1] * self.n
		self.head = [0] * self.n
		self.preord = [0] * self.n
		self.k = 0
		for v in range(self.n):
			if self.par[v] == -1:
				self.dfs_sz(v)
				self.dfs_hld(v)
	
	def dfs_sz(self, v: int) -> None:
		G = self.G
		stack, order = [v], [v]
		while stack:
			p = stack.pop()
			for u in G[p]:
				if self.par[p] == u: continue
				self.par[u] = p
				stack.append(u)
				order.append(u)
		while order:
			p = order.pop()
			ch = G[p]
			if len(ch) and ch[0] == self.par[p]:
				ch[0], ch[-1] = ch[-1], ch[0]
			for i, u in enumerate(ch):
				if u == self.par[p]: continue
				self.size[p] += self.size[u]
				if self.size[u] > self.size[ch[0]]:
					ch[i], ch[0] = ch[0], ch[i]
	
	def dfs_hld(self, v: int) -> None:
		G = self.G
		stack = [v]
		while stack:
			p = stack.pop()
			self.preord[p] = self.k
			self.k += 1
			top = self.G[p][0]
			for u in G[p][::-1]:
				if u == self.par[p]: continue
				if u == top:
					self.head[u] = self.head[p]
				else:
					self.head[u] = u
				stack.append(u)

	def enumerate_vertices(self, u: int, v: int) -> typing.Generator[typing.Tuple[int, int], None, None]:
		while True:
			if self.preord[u] > self.preord[v]: u, v = v, u
			l = max(self.preord[self.head[v]], self.preord[u])
			r = self.preord[v]
			yield l, r
			if self.head[u] != self.head[v]:
				v = self.par[self.head[v]]
			else:
				return
	
	def enumerate_edges(self, u: int, v: int) -> typing.Generator[typing.Tuple[int, int], None, None]:
		while True:
			if self.preord[u] > self.preord[v]: u, v = v, u
			if self.head[u] != self.head[v]:
				yield self.preord[self.head[v]], self.preord[v]
				v = self.par[self.head[v]]
			else:
				if u != v:
					yield self.preord[u] + 1, self.preord[v]
				break

	def subtree(self, v: int) -> typing.Tuple[int, int]:
		l = self.preord[v]
		r = self.preord[v] + self.size(v)
		return l, r

	def lowest_common_ancestor(self, u: int, v: int) -> int:
		while True:
			if self.preord[u] > self.preord[v]: u, v = v, u
			if self.head[u] == self.head[v]: return u
			v = self.par[self.head[v]]

# 行列ライブラリ(遅い)
class Matrix:
	def __init__(self, n: int, m: int, mat: typing.Union[list, None] = None, mod: int = 998244353) -> None:
		self.n = n
		self.m = m
		self.mat = [[0] * self.m for i in range(self.n)]
		self.mod = mod
		if mat:
			for i in range(self.n):
				self.mat[i] = mat[i]
	
	def is_square(self) -> None:
		return self.n == self.m
	
	def __getitem__(self, key: int) -> int:
		if isinstance(key, slice):
			return self.mat[key]
		else:
			assert key >= 0
			return self.mat[key]

	@classmethod
	def id(n: int) -> "Matrix":
		res = Matrix(n, n)
		for i in range(n):
			res[i][i] = 1
		return res

	def __len__(self) -> int:
		return len(self.mat)
	
	def __str__(self) -> str:
		return "\n".join(" ".join(map(str, self[i])) for i in range(self.n))

	def times(self, k: int) -> "Matrix":
		res = [[0] * self.m for i in range(self.n)]
		for i in range(self.n):
			for j in range(self.m):
				res[i][j] = k * self[i][j] % self.mod
		return Matrix(self.n, self.m, res)

	def __pos__(self) -> "Matrix":
		return self

	def __neg__(self) -> "Matrix":
		return self.times(-1)

	def __add__(self, other: "Matrix") -> "Matrix":
		res = [[0] * self.m for i in range(self.n)]
		for i in range(self.n):
			for j in range(self.m):
				res[i][j] = (self[i][j] + other[i][j]) % self.mod
		return Matrix(self.n, self.m, res)
	
	def __sub__(self, other: "Matrix") -> "Matrix":
		res = [[0] * self.m for i in range(self.n)]
		for i in range(self.n):
			for j in range(self.m):
				res[i][j] = (self[i][j] - other[i][j]) % self.mod
		return Matrix(self.n, self.m, res)

	def __mul__(self, other: typing.Union["Matrix", int]) -> "Matrix":
		if other.__class__ == Matrix:
			res = [[0] * other.m for i in range(self.n)]
			for i in range(self.n):
				for k in range(self.m):
					for j in range(other.m):
						res[i][j] += self[i][k] * other[k][j]
						res[i][j] %= self.mod
			return Matrix(self.n, other.m, res)
		else:
			return self.times(other)
	
	def __rmul__(self, other: typing.Union["Matrix", int]) -> "Matrix":
		return self.times(other)

	def __pow__(self, k: int) -> "Matrix":
		tmp = Matrix(self.n, self.n, self.mat)
		res = Matrix.id(self.n)
		while k:
			if k & 1:
				res *= tmp
			tmp *= tmp
			k >>= 1
		return res

	def determinant(self) -> int:
		res = 1
		tmp  = Matrix(self.n, self.n, self.mat)
		for j in range(self.n):
			if tmp[j][j] == 0:
				for i in range(j + 1, self.n):
					if tmp[i][j] != 0: break
				else:
					return 0
				tmp.mat[j], tmp.mat[i] = tmp.mat[i], tmp.mat[j]
				res *= -1
			inv = invmod(tmp[j][j], self.mod)
			for i in range(j + 1, self.n):
				c = -inv * tmp[i][j] % self.mod
				for k in range(self.n):
					tmp[i][k] += c * tmp[j][k]
					tmp[i][k] %= self.mod
		for i in range(self.n):
			res *= tmp[i][i]
			res %= self.mod
		return res

# 多項式補間
class PolynomialInterpolation:
	def __init__(self, mod: int = 99824353) -> None:
		self.mod = mod

	# 多項式補間(普通の O(N^2))
	def polynomial_interpolation(self, X: list, Y: list, t: int) -> int:
		n = len(X) - 1
		c = [0] * (n + 1)
		for i, xi in enumerate(X):
			f = 1
			for j, xj in enumerate(X):
				if i == j: continue
				f *= (xi - xj)
				f %= self.mod
			c[i] = (Y[i] * invmod(f, self.mod)) % self.mod
		res = 0
		f = 1
		for i, x in enumerate(X):
			f *= (t - x)
			f %= self.mod
		for i, a in enumerate(c):
			res += a * f * invmod(t - X[i], self.mod) % self.mod
			res %= self.mod
		return res

	# 多項式補間(等差の O(N log N))
	def polynomial_interpolation_arithmetic(self, a: int, d: int, Y: list, t: int) -> int:
		n = len(Y) - 1
		C = [0] * (n + 1)
		f = 1
		for i in range(1, n + 1):
			f *= -d * i
			f %= self.mod
		C[0] = (Y[0] * invmod(f, self.mod)) % self.mod
		for i in range(1, n + 1):
			f *= invmod(-d * (n - i + 1), self.mod) * d * i
			f %= self.mod
			C[i] = (Y[i] * invmod(f, self.mod)) % self.mod
		res = 0
		f = 1
		for i in range(n + 1):
			f = f * (t - (a + d * i))
			f %= self.mod
		for i, c in enumerate(C):
			res += c * f * invmod(t - (a + d * i), self.mod)
			res %= self.mod
		return res

# 拡張Euclidの互除法
def extgcd(a: int, b: int, d: int = 0) -> typing.Tuple[int, int, int]:
	g = a
	if b == 0:
		x, y = 1, 0
	else:
		x, y, g = extgcd(b, a % b)
		x, y = y, x - a // b * y
	return x, y, g
 
# mod p における逆元
def invmod(a: int, p: int) -> int:
	x, y, g = extgcd(a, p)
	x %= p
	return x

# モンモール数（長さ N の完全順列の数）を 1,2,...,N について求める
def montmort_number(n: int, mod: int = 998244353) -> list:
	res = [0]
	for i in range(2, n + 1): res.append((i * res[-1] + pow(-1, i)) % mod)
	return res

# 偏角ソート
def sort_by_argument(points: list) -> list:
	def compare(p1: tuple, p2: tuple) -> int:
		x1, y1 = p1; x2, y2 = p2
		tmp = x1 * y2 - y1 * x2
		if tmp < 0: return 1
		elif tmp > 0: return -1
		else: return 0

	quad = [[] for i in range(4)]

	for x, y in points:
		if x == y == 0: quad[2].append((x, y))
		elif x <= 0 and y < 0: quad[0].append((x, y))
		elif x > 0 and y <= 0: quad[1].append((x, y))
		elif x >= 0 and y > 0: quad[2].append((x, y))
		else: quad[3].append((x, y))
	
	res = []

	for i in range(4):
		quad[i].sort(key=functools.cmp_to_key(compare))
		for point in quad[i]: res.append(point)
	
	return res

# SlidingWindowAggretgation
class SlidingWindowAggretgation:
	def __init__(self, op: typing.Callable[[typing.Any, typing.Any], typing.Any]) -> None:
		self.op = op
		self.front_stack = []
		self.back_stack = []

	def __len__(self) -> int:
		return len(self.front_stack) + len(self.back_stack)

	def __bool__(self) -> bool:
		return len(self) > 0

	def __str__(self) -> str:
		data = [x for x, _ in self.front_stack][::-1] + [x for x, _ in self.back_stack]
		return str(data)

	def append(self, x: int) -> None:
		fx = x
		if self.back_stack:
			fx = self.op(self.back_stack[-1][1], x)
		self.back_stack.append((x, fx))

	def popleft(self) -> None:
		if not self.front_stack:
			x = fx = self.back_stack.pop()[0]
			self.front_stack.append((x, fx))
			while self.back_stack:
				x = self.back_stack.pop()[0]
				fx = self.op(x, fx)
				self.front_stack.append((x, fx))
		self.front_stack.pop()

	def all_prod(self) -> int:
		res = None
		if self.front_stack:
			res = self.front_stack[-1][1]
		if self.back_stack:
			if res is None:
				res = self.back_stack[-1][1]
			else:
					res = self.op(res, self.back_stack[-1][1])
		return res

# トポロジカルソート
def topological_sort(G: list, d: list) -> list:
	n = len(G)
	s = []
	for i in range(n):
		if d[i] == 0: s.append(i)
	ans = []
	while s:
		u = s.pop()
		ans.append(u)
		for v in G[u]:
			d[v] -= 1
			if d[v] == 0: s.append(v)
	if len(ans) != n: return -1
	return ans

# maxflow (Ford-Fulkerson)
class FordFulkerson:
	def __init__(self, n: int) -> None:
		self.n = n
		self.G = [[] for _ in range(n)]
		self.inf = 10 ** 9
	
	def add_edge(self, fr: int, to: int, cap: int) -> None:
		fwd = [to, cap, None]
		fwd[2] = bwd = [fr, 0, fwd]
		self.G[fr].append(fwd)
		self.G[to].append(bwd)
		
	def add_multi_edge(self, v1, v2, cap1, cap2):
		edge1 = [v2, cap1, None]
		edge1[2] = edge2 = [v1, cap2, edge1]
		self.G[v1].append(edge1)
		self.G[v2].append(edge2)
	
	def dfs(self, v: int, t: int, f: int) -> int:
		if v == t: return f
		self.visit[v] = 1
		for e in self.G[v]:
			w, cap, rev = e
			if cap and not self.visit[w]:
				d = self.dfs(w, t, min(f, cap))
				if d:
					e[1] -= d
					rev[1] += d
					return d
		return 0
	
	def max_flow(self, s: int, t: int) -> int:
		flow = 0
		f = self.inf
		while f:
			self.visit = [0] * self.n
			f = self.dfs(s, t, self.inf)
			flow += f
		return flow

# maxflow (Dinic)
class Dinic:
	def __init__(self, n: int) -> None:
		self.n = n
		self.G = [[] for _ in range(n)]
		self.inf = 10 ** 9
	
	def add_edge(self, fr: int, to: int, cap: int) -> None:
		fwd = [to, cap, None]
		fwd[2] = bwd = [fr, 0, fwd]
		self.G[fr].append(fwd)
		self.G[to].append(bwd)
		
	def add_multi_edge(self, v1: int, v2: int, cap1: int, cap2: int) -> None:
		edge1 = [v2, cap1, None]
		edge1[2] = edge2 = [v1, cap2, edge1]
		self.G[v1].append(edge1)
		self.G[v2].append(edge2)
		
	def bfs(self, s: int, t: int) -> bool:
		self.level = [None] * self.n
		Q = collections.deque([s])
		self.level[s] = 0
		while Q:
			u = Q.popleft()
			lv = self.level[u] + 1
			for v, cap, _ in self.G[u]:
				if cap and self.level[v] is None:
					self.level[v] = lv
					Q.append(v)
		return self.level[t] is not None
	
	def dfs(self, v: int, t: int, f: int) -> int:
		if v == t: return f
		for e in self.it[v]:
			w, cap, rev = e
			if cap and self.level[v] < self.level[w]:
				d = self.dfs(w, t, min(f, cap))
				if d:
					e[1] -= d
					rev[1] += d
					return d
		return 0
	
	def max_flow(self, s: int, t: int) -> int:
		flow = 0
		while self.bfs(s, t):
			*self.it, = map(iter, self.G)
			f = self.inf
			while f:
				f = self.dfs(s, t, self.inf)
				flow += f
		return flow

n, m = map(int, input().split())
s = set()
cx, cy = 1, 1
for _ in range(min(2 * 10 ** 5, n * 100)):
	for i in range(3):
		for j in range(3):
			if cx + i <= n and cy + j <= m: s.add((cx + i, cy + j))
	cx = min(cx + 2, n); cy = min(cy + 2, m)
print(len(s))
for a, b in s: print(a, b)